package com.example.kotlin_final

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class IboHelper(var context:Context, var name:String, var version:Int):SQLiteOpenHelper(context,name,null,version) {
    val table_name="uyeler"
    val id_column_create="id integer primary key autoincrement"
    val eposta_column_create="eposta varchar(256)"
    val sifre_column_create="sifre varchar(256)"
    val create_table_sql="create table $table_name( $id_column_create,$eposta_column_create,$sifre_column_create);"

    val eposta_column_name="eposta"
    val sifre_column_name="sifre"
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(create_table_sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    fun insertUye(eposta:String,sifre:String){
        val contentValues = ContentValues()
        val database=writableDatabase
        contentValues.put(eposta_column_name, eposta)
        contentValues.put(sifre_column_name, sifre)
        val result = database.insert(table_name, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "ekleme basarisiz", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "ekleme basarili", Toast.LENGTH_SHORT).show()
        }
    }
    fun uyeExists(eposta:String,sifre:String):Boolean{
        val db = this.writableDatabase
        val selectQuery = "SELECT  * FROM $table_name WHERE $eposta_column_name = '$eposta' and $sifre_column_name = '$sifre'"
        db.rawQuery(selectQuery, null).use { // .use requires API 16
            if (it.moveToFirst()) {
                val eposta_result = it.getInt(it.getColumnIndex(eposta_column_name))
                val sifre_result = it.getString(it.getColumnIndex(sifre_column_name))
                return true
            }
        }
        return false
    }
}