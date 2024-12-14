package com.example.plantapp.di

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.plantapp.PlantApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Hilt'e bu modülün uygulama seviyesinde geçerli olduğunu belirtiyoruz
object AppModule {

    /** kullanacağımız bağımlılıkların tanımlanması */

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): PlantApplication {
        return app as PlantApplication
    }
}