package com.example.practicatiendaandroid.Data.Injections

import android.content.Context
import androidx.room.Room
import com.example.practicatiendaandroid.Data.Database.ShopDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context)= Room.databaseBuilder(context, ShopDatabase::class.java, "shop_database").build()

    @Singleton
    @Provides
    fun provideProductDao(database:ShopDatabase)= database.getProductDao()

    @Singleton
    @Provides
    fun provideCartDao(database:ShopDatabase)= database.getCartDao()

}