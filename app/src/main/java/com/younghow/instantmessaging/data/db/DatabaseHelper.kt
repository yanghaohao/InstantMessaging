package com.younghow.instantmessaging.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.younghow.instantmessaging.app.IMApplication
import org.jetbrains.anko.db.*

class DatabaseHelper(context: Context = IMApplication.instance) : ManagedSQLiteOpenHelper(context, NAME,null, VERSION){
    companion object{
       val NAME = "im.db"
        val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(ContactTable.NAME,true,ContactTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,ContactTable.CONTACT to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(ContactTable.NAME,true)
        onCreate(db)
    }
}