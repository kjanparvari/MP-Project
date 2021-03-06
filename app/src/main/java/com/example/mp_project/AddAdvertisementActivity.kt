package com.example.mp_project

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.mp_project.databinding.ActivityAddAdvertisementBinding
import com.example.mp_project.databinding.DialogImageSelectionBinding
import com.example.mp_project.serverModel.advertisements.ApiAdvertisementsListInterface
import com.example.mp_project.serverModel.books.ApiBooksListInterface
import com.example.mp_project.serverModel.books.BooksListItem
import com.example.mp_project.serverModel.imageUpload.ApiImageUpload
import com.example.mp_project.serverModel.imageUpload.ImageUploadResponse
import com.example.mp_project.serverModel.requestJson.RequestAddAdvertisement
import com.example.mp_project.serverModel.requestJson.RequestJsonId
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import com.squareup.picasso.Picasso
import okhttp3.MediaType
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*
import okhttp3.RequestBody


private lateinit var mBinding: ActivityAddAdvertisementBinding
private var mImagePath: String = ""

class AddAdvertisementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAddAdvertisementBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupActionBar()

        mBinding.ivAddAdvertisementImage.setOnClickListener {
            imageSelectDialog()
        }

        mBinding.bRegisterAdvertisement.setOnClickListener {
            val cTitle = mBinding.etTitle.text.toString().trim { it <= ' ' }
            val cBookName = mBinding.etBookName.text.toString().trim { it <= ' ' }
            val cPhoneNumber = mBinding.etPhoneNumber.text.toString().trim { it <= ' ' }
            val cPrice = Integer.parseInt(mBinding.etPrice.text.toString())
            val cCity = mBinding.etCity.text.toString().trim { it <= ' ' }
            val cDes = mBinding.etDescription.text.toString().trim { it <= ' ' }

            when {
                TextUtils.isEmpty(cTitle) -> {

                }
                TextUtils.isEmpty(cBookName) -> {

                }
                TextUtils.isEmpty(cPhoneNumber) -> {

                }

                TextUtils.isEmpty(cDes) -> {

                }
                TextUtils.isEmpty(mImagePath) -> {

                }
                else -> {
                    val retrofitBuilder = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
//                        .baseUrl(BASE_URL)
                        .baseUrl(BASE_URL)
                        .build()
                        .create(ApiImageUpload::class.java)

                    val file = File(mImagePath)

                    val fbody = RequestBody.create(
                        MediaType.parse("multipart/form-data"),
                        file
                    )

                    val retrofitData = retrofitBuilder.uploadImage(
                        MultipartBody.Part.createFormData(
                            "image",
                            file.name,
                            fbody
                        )
                    )

                    retrofitData.enqueue(object : Callback<ImageUploadResponse?> {
                        override fun onResponse(
                            call: Call<ImageUploadResponse?>,
                            response: Response<ImageUploadResponse?>
                        ) {
                            val responseBody = response.body()!!
                            Log.d("Program imageUpload", responseBody.toString())

                            val retrofitReqData = Retrofit.Builder()
                                .addConverterFactory(GsonConverterFactory.create())
//                        .baseUrl(BASE_URL)
                                .baseUrl(BASE_URL)
                                .build()
                                .create(ApiAdvertisementsListInterface::class.java).sendAd(
                                    RequestAddAdvertisement(
                                        "61cdb9a9a99b218ac4946ea1",
                                        cPrice,
                                        cCity,
                                        cDes,
                                        cPhoneNumber,responseBody.imageUrl,
                                        cTitle
                                    )
                                )
                        }

                        override fun onFailure(call: Call<ImageUploadResponse?>, t: Throwable) {
                            Log.d("Program", "Failure: " + t.message)
                        }
                    })

                }
            }
        }
    }

    private fun setupActionBar() {
        setSupportActionBar(mBinding.addAdvertisementToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.addAdvertisementToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    private fun imageSelectDialog() {
        val dialog = Dialog(this)
        val binding: DialogImageSelectionBinding =
            DialogImageSelectionBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        binding.tvCamera.setOnClickListener {
            Dexter.withContext(this).withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report?.let {
                        if (report.areAllPermissionsGranted()) {
                            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            startActivityForResult(intent, CAMERA)
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
//                    showRationalDialogForPermissions()
                }

            }).onSameThread().check()
            dialog.dismiss()
        }

        binding.tvGallery.setOnClickListener {
            Dexter.withContext(this).withPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE
            ).withListener(object : PermissionListener {
                override fun onPermissionGranted(report: PermissionGrantedResponse?) {
                    val galleryIntent =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(galleryIntent, GALLERY)
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    Toast.makeText(
                        this@AddAdvertisementActivity,
                        "Gallery Denied",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {
//                    showRationalDialogForPermissions()
                }
            }).onSameThread().check()
            dialog.dismiss()
        }

        dialog.show()


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA) {
                data?.extras?.let {
                    val thumbnail: Bitmap = data.extras!!.get("data") as Bitmap

                    mImagePath = saveImageToInternalStorage(thumbnail)

                    Glide.with(this).load(mImagePath).centerCrop()
                        .into(mBinding.ivAdvertisementImage)

                }
            }
            if (requestCode == GALLERY) {
                data?.let {
                    val selectedPhotoUri = data.data

                    Glide.with(this)
                        .load(selectedPhotoUri)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                Log.e("Tag", "Error loading Image", e)
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                resource?.let {
                                    val bitmap: Bitmap = resource.toBitmap()
                                    mImagePath = saveImageToInternalStorage(bitmap)
                                    Log.i("ImagePath", mImagePath)

                                }
                                return false
                            }

                        })
                        .centerCrop()
                        .into(mBinding.ivAdvertisementImage)
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.e("Cancelled", "Image selection Cancelled")
        }
    }

    private fun saveImageToInternalStorage(bitmap: Bitmap): String {
        val wrapper = ContextWrapper(applicationContext)

        var file = wrapper.getDir(IMAGE_DIRECTORY, Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.jpg")

        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return file.absolutePath
    }


    companion object {
        private const val CAMERA = 1
        private const val GALLERY = 2

        private const val IMAGE_DIRECTORY = "Ghafase"
    }

}