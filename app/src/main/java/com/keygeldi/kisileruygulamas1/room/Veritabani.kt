package com.keygeldi.kisileruygulamas1.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.keygeldi.kisileruygulamas1.data.entity.Kisiler

@Database(entities = [Kisiler::class], version = 1)
abstract class Veritabani : RoomDatabase() {
    abstract fun getKisilerDao() : KisilerDao
}