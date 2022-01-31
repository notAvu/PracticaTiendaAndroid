package com.example.practicatiendaandroid.Data.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.practicatiendaandroid.Clases.Cart

@Entity(tableName = "cart_table")
data class CartEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id:Int=0,
    @ColumnInfo(name="status")val status:Boolean
)
 fun Cart.toDatabase()=CartEntity(id=id,status = status)