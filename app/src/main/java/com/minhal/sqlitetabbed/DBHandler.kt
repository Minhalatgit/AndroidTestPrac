package com.minhal.sqlitetabbed

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler (private val context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        var CREATE_TABLE="CREATE TABLE $TABLE_NAME ( $COL_NAME VARCHAR(40),$COL_EMAIL VARCHAR(40))"
        db!!.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    }
    fun insert(user: User):Boolean{
        var db=this.writableDatabase
        var values=ContentValues()
        values.put(COL_NAME,user.name)
        values.put(COL_EMAIL,user.email)
        var success=db.insert(TABLE_NAME,null,values)
        return (Integer.parseInt("$success") !=-1)
    }
    fun getAllUsers(): String {
        var allUser: String = "";
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectALLQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    var name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                    var email = cursor.getString(cursor.getColumnIndex(COL_EMAIL))

                    allUser = "$allUser\n $name $email"
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return allUser
    }

    companion object{
        const val DATABASE_VERSION=1
        const val DATABASE_NAME="MyDB"
        const val TABLE_NAME="users"
        const val COL_NAME="name"
        const val COL_EMAIL="email"
    }
}