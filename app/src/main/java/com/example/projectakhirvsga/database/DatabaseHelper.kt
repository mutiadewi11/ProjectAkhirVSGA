package com.example.projectakhirvsga.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.content.ContentValues

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE $TABLE_NEWS (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_TITLE TEXT, " +
                    "$COLUMN_CONTENT TEXT, " +
                    "$COLUMN_PATH_IMAGE TEXT)"
        )

        db.execSQL(
            "CREATE TABLE $TABLE_ALUMNI (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_NIM TEXT, " +
                    "$COLUMN_NAMA TEXT, " +
                    "$COLUMN_TEMPAH_LAHIR TEXT, " +
                    "$COLUMN_TANGGAL_LAHIR TEXT, " +
                    "$COLUMN_ALAMAT TEXT, " +
                    "$COLUMN_AGAMA TEXT, " +
                    "$COLUMN_TELEPON TEXT, " +
                    "$COLUMN_TAHUN_MASUK INTEGER, " +
                    "$COLUMN_TAHUN_LULUS INTEGER, " +
                    "$COLUMN_PEKERJAAN TEXT, " +
                    "$COLUMN_JABATAN TEXT)"
        )

        // Insert dummy data
        insertDummyData(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NEWS")
        onCreate(db)
    }

    private fun insertDummyData(db: SQLiteDatabase) {
        val titles = listOf(
            "BEAUTY buffs are racing to Lidl for brand new beauty tools that won’t break the bank.",
            "I tried the new Look Fantastic x Nuxe beauty box - here's what I thought",
            "BABTAC launches 34-strong Beauty Collective to elevate industry standards",
            "Benefit Cosmetics host unmissable beauty pop-up with deluxe prizes to be won",
            "ELLE Beauty Director Can’t Get Enough Of This Cleanser.",
            "Viral Beauty Products That Are Really Affordable",
            "My Favorite New Fashion and Beauty Products from Amazon",
            "Beauty doctor's five top tips to achieve younger skin without any injections",
            "Derbyshire hair and beauty business racks up ten nominations at national Salon Awards 2024",
            "BEAUTY lovers have been wowed by a budget-friendly gel that transforms unruly eyebrows."
        )

        val contents = listOf(
            "In the “middle of Lidl” this week, you’ll find hair dryers, curling irons that may look familiar and even some branded hygiene goodies. ",
            "As a beauty journalist, I've written countless articles on a myriad of products over the years — some brands have become my staples (hello, Jo Malone and Neom) while others are familiar but remain untested.",
            "Designed to help better educate the beauty industry and elevate sector standards, the Collective is led by BABTAC CEO, Lesley Blair MBE. Its focus is on supporting new and established beauty professionals, improving consumer care and enhancing the beauty industry’s reputation.",
            "Be sure to pen the 22nd August in your diary, because it’s time to get shopping. Yep, you heard that right, Benefit are soon to be hosting an exclusive pop-up in Selfridges Beauty Hall for a limited time – and trust us, this is one you’re not going to want to miss.",
            "ne thing I love to do every single evening, in fact it’s a little bit of a ritual of mine, is to double cleanse. I find it’s the best way to get my skin prepped and ready for any serums and moisturisers I want to put on that evening. And one cleansing balm I’m loving at the moment is the endota Ceramide Cleansing Balm.",
            "Beauty products that are actually affordable? Sign me up! These bestselling viral beauty finds can be bought right on Amazon, and won’t break the bank!",
            "Fashion, accessories, and beauty galore! Shop this week’s roundup of my top favorite finds on Amazon. I mean, is there anything better than getting everything you need in one place?",
            "Dr Keyana Emamian is a general practitioner and an aesthetic specialist at Este Medical Group and has spent almost two decades in the beauty industry helping people achieve their skincare goals.",
            "Aspire Creative, on Bank Road in Matlock, is in contention for a bumper night at the Salon Awards 2024 in November, after the shortlist was announced this week which recognised many excellent strands to the business.",
            "From hormonal changes to skin conditions, there are various reasons why eyebrows may not grow in the shape and thickness desired."
        )

        val imageResourceIds = listOf(
            "drawable/hairdryer",
            "drawable/nuxe",
            "drawable/babtac",
            "drawable/msn",
            "drawable/elle",
            "drawable/amazon",
            "drawable/fashion",
            "drawable/skin",
            "drawable/hair",
            "drawable/eye"
        )

        for (i in titles.indices) {
            val values = ContentValues().apply {
                put(COLUMN_TITLE, titles[i])
                put(COLUMN_CONTENT, contents[i])
                put(COLUMN_PATH_IMAGE, imageResourceIds[i])
            }

            // Log values for debugging
            Log.d("DatabaseHelper", "Inserting data: $values")

            val rowId = db.insert(TABLE_NEWS, null, values)
            if (rowId == -1L) {
                Log.e("DatabaseHelper", "Failed to insert row for title: ${titles[i]}")
            } else {
                Log.d("DatabaseHelper", "Inserted row with ID: $rowId")
            }
        }
    }


    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "alumni.db"

        // News table
        const val TABLE_NEWS = "news"
        const val COLUMN_ID = "_id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_CONTENT = "content"
        const val COLUMN_PATH_IMAGE = "path_image"

        // Alumni table
        const val TABLE_ALUMNI = "alumni"
        const val COLUMN_NIM = "nim"
        const val COLUMN_NAMA = "nama"
        const val COLUMN_TEMPAH_LAHIR = "tempat_lahir"
        const val COLUMN_TANGGAL_LAHIR = "tanggal_lahir"
        const val COLUMN_ALAMAT = "alamat"
        const val COLUMN_AGAMA = "agama"
        const val COLUMN_TELEPON = "telepon"
        const val COLUMN_TAHUN_MASUK = "tahun_masuk"
        const val COLUMN_TAHUN_LULUS = "tahun_lulus"
        const val COLUMN_PEKERJAAN = "pekerjaan"
        const val COLUMN_JABATAN = "jabatan"
    }
}