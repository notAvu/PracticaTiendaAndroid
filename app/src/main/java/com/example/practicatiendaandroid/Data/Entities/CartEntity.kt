package com.example.practicatiendaandroid.Data.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "cart_table")
data class CartEntity (
    @ColumnInfo(name = "id") val id:Int=0,
    @ColumnInfo(name="status")val status:Boolean
)