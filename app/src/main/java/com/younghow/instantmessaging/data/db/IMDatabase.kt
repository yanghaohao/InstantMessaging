package com.younghow.instantmessaging.data.db

import com.younghow.instantmessaging.data.Contact
import com.younghow.instantmessaging.extentions.toVarargArray
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class IMDatabase {
    companion object{
        val databaseHelper = DatabaseHelper()
        val instance =  IMDatabase()
    }

    fun saveContact(contact:Contact) =
        databaseHelper.use {
            insert(ContactTable.NAME,*contact.map.toVarargArray())
        }


    fun getAllContact() : List<Contact> =
        databaseHelper.use {
            select(ContactTable.NAME).parseList(object  : MapRowParser<Contact>{
                override fun parseRow(columns: Map<String, Any?>): Contact  = Contact(columns.toMutableMap())
            })
        }

    fun deleteAllContact()=
        databaseHelper.use {
            delete(ContactTable.NAME,null,null)
        }


}