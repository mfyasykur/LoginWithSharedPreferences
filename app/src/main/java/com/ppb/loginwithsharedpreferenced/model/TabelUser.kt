package com.ppb.loginwithsharedpreferenced.model

import android.provider.BaseColumns

object TabelUser {
    class UserTable: BaseColumns {
        companion object {
            val TABLE_NAME = "user"
            val COL_EMAIL = "email"
            val COL_PASSWORD = "password"
            val COL_FULLNAME = "fullname"
            val COL_JUMLAH = "jumlah"
        }
    }
}