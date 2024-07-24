package com.example.roomdatabaseexample.di

import android.content.Context
import androidx.room.Room
import com.example.roomdatabaseexample.repository.UserRepository
import com.example.roomdatabaseexample.db.AppDatabase
import com.example.roomdatabaseexample.db.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context = context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }

    @Provides
    fun provideUserRepository(userDao: UserDao) = UserRepository(userDao)
}