package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class TestOpenHelper internal constructor(context: Context?) :


    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {



    override fun onCreate(db: SQLiteDatabase) {

        // テーブル作成
        // SQLiteファイルがなければSQLiteファイルが作成される
        db.execSQL(
            SQL_CREATE_ENTRIES
        )

        db.execSQL(
            SQL_CREATE_ENTRIES1
        )

        Log.d("debug", "onCreate(SQLiteDatabase db)")


     }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // アップデートの判別
        db.execSQL(
            SQL_DELETE_ENTRIES
        )
        db.execSQL(
            SQL_DELETE_ENTRIES1,
        )
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }


    companion object {
        // データーベースのバージョン
        private const val DATABASE_VERSION = 1

        // データーベース名
        private const val DATABASE_NAME = "test14.db"
        private const val TABLE_NAME1 = "account"
        private const val TABLE_NAME2 = "shop"
        private const val _ID = "_id"
        private const val COLUMN_USER_ID = "userid"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_BIRTH = "birth"

        private const val COLUMN_SHOP_ID = "shop_id"
        private const val COLUMN_SHOP_NAME = "shop_name"
        private const val COLUMN_SHOP_ADDRESS = "address"


        //アカウント
        private const val SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME1 + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_USER_ID + " TEXT," +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_BIRTH + " TEXT)"


        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME1

        //お店
        private const val SQL_CREATE_ENTRIES1 = "CREATE TABLE " + TABLE_NAME2 + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_SHOP_ID + " TEXT," +
                COLUMN_SHOP_NAME + " TEXT," +
                COLUMN_SHOP_ADDRESS + " TEXT)"
        private const val SQL_DELETE_ENTRIES1 = "DROP TABLE IF EXISTS " + TABLE_NAME2

    }

    }

