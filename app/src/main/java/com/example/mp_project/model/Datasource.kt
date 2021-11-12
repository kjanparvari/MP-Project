package com.example.mp_project.model

import com.example.mp_project.R

val sampleBooks = listOf<Book>(
    Book(
        R.drawable.the_faut_in_our_stars,
        "اشتباه در ستاره های بخت ما",
        "John Green",
        "Novel",
        4.8,
        "The Fault In Our Stars is a fabulous book about a young teenage girl who has been diagnosed with lung cancer and attends a cancer support group. Hazel is 16 and is reluctant to go to the support group, but she soon realises that it was a good idea. Hazel meets a young boy named Augustus Waters.",
        listOf<Comment>(),
        listOf<Announcement>()
    ),
    Book(
        R.drawable.harry_potter_and_the_philosopher_s_stone,
        "هری پاتر و سنگ جادو",
        "J.K. Rowling",
        "Novel",
        4.7,
        "It is a story about Harry Potter, an orphan brought up by his aunt and uncle because his parents were killed when he was a baby. Harry is unloved by his uncle and aunt but everything changes when he is invited to join Hogwarts School of Witchcraft and Wizardry and he finds out he's a wizard.",
        listOf<Comment>(),
        listOf<Announcement>()
    ),
)

val sampleUsers = listOf<User>(
    User(
        "Jacob",
        "0848514685454",
        "Tehran",
        "1397-07-23"
    ),
)

val sampleAnnouncements = listOf<Announcement>(
    Announcement(
        sampleBooks.first(),
        sampleUsers.first(),
        60000,
        "1400-07-15",
        "sample description to sell this book"
    ),
    Announcement(
        sampleBooks[1],
        sampleUsers.first(),
        45000,
        "1400-05-12",
        "sample description to sell this book"
    ),
    Announcement(
        sampleBooks.first(),
        sampleUsers.first(),
        60000,
        "1400-07-15",
        "sample description to sell this book"
    ),
    Announcement(
        sampleBooks[1],
        sampleUsers.first(),
        45000,
        "1400-05-12",
        "sample description to sell this book"
    ),
    Announcement(
        sampleBooks.first(),
        sampleUsers.first(),
        60000,
        "1400-07-15",
        "sample description to sell this book"
    ),
    Announcement(
        sampleBooks[1],
        sampleUsers.first(),
        45000,
        "1400-05-12",
        "sample description to sell this book"
    ),
    Announcement(
        sampleBooks.first(),
        sampleUsers.first(),
        60000,
        "1400-07-15",
        "sample description to sell this book"
    ),
    Announcement(
        sampleBooks[1],
        sampleUsers.first(),
        45000,
        "1400-05-12",
        "sample description to sell this book"
    ),
)

class Datasource {
    fun loadBooks(): List<Book> {
        return sampleBooks
    }

    fun loadAnnouncements(): List<Announcement> {
        return sampleAnnouncements
    }

    fun loadUsers(): List<User> {
        return sampleUsers
    }
}