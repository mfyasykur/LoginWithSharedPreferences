package com.ppb.loginwithsharedpreferenced.connection

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ppb.loginwithsharedpreferenced.model.TabelUser

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        val DATABASE_NAME = "myapp.db"
        val DATABASE_VERSION = 1

        private val SQL_CREATE_USER = "CREATE TABLE ${TabelUser.UserTable.TABLE_NAME} (${TabelUser.UserTable.COL_EMAIL} VARCHAR(200) PRIMARY KEY, ${TabelUser.UserTable.COL_PASSWORD} TEXT, ${TabelUser.UserTable.COL_FULLNAME} TEXT)"

        private val SQL_DELETE_QUERY = "DROP TABLE IF EXISTS ${TabelUser.UserTable.TABLE_NAME}"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun RegisterUser(emailIn: String, passwordIn: String, fullnameIn: String){

        val db = writableDatabase
        val namaTabel = TabelUser.UserTable.TABLE_NAME
        val email_tab = TabelUser.UserTable.COL_EMAIL
        val password_tab = TabelUser.UserTable.COL_PASSWORD
        val fullname_tab = TabelUser.UserTable.COL_FULLNAME

        val sql = "INSERT INTO $namaTabel($email_tab, $password_tab, $fullname_tab) VALUES (?, ?, ?)"

        db.execSQL(sql, arrayOf(emailIn, passwordIn, fullnameIn))
    }

    @SuppressLint("Range")
    fun cekUser(emailIn: String): String {

        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""

        try {
            cursor = db.rawQuery("SELECT COUNT(${TabelUser.UserTable.COL_EMAIL}) as jumlah FROM ${TabelUser.UserTable.TABLE_NAME} WHERE ${TabelUser.UserTable.COL_EMAIL} == '$emailIn'", null)
        } catch (ex: SQLException) {
            db.execSQL(SQL_CREATE_USER)

            return "Tabel dibuat"
        }

        if (cursor!!.moveToFirst()) {
            jumlah = cursor.getString(cursor.getColumnIndex(TabelUser.UserTable.COL_JUMLAH))
        }

        return jumlah
    }

    @SuppressLint("Range")
    fun cekLogin(emailIn: String, passwordIn: String): String {

        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""

        try {
            cursor = db.rawQuery("SELECT COUNT(${TabelUser.UserTable.COL_EMAIL}) as jumlah FROM ${TabelUser.UserTable.TABLE_NAME} WHERE ${TabelUser.UserTable.COL_EMAIL} == '$emailIn' AND ${TabelUser.UserTable.COL_PASSWORD} == '$passwordIn'", null)
        } catch (ex: SQLException) {
            db.execSQL(SQL_CREATE_USER)

            return "Tabel dibuat"
        }

        if (cursor!!.moveToFirst()) {
            jumlah = cursor.getString(cursor.getColumnIndex(TabelUser.UserTable.COL_JUMLAH))
        }

        return jumlah
    }
}