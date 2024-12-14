package com.example.plantapp.di


import com.example.plantapp.data.dto.remote.PlantApi
import com.example.plantapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Hilt'e bu modülün uygulama seviyesinde geçerli olduğunu belirtiyoruz
object NetworkModule {

    /** kullanacağımız bağımlılıkların tanımlanması */
    /** Api isteği */
    @Provides
    @Singleton
    fun providePlantApi(): PlantApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlantApi::class.java)
    }

}