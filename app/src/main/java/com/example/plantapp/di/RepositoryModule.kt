package com.example.plantapp.di

import android.content.Context
import android.util.Log
import com.example.plantapp.data.dto.remote.PlantApi
import com.example.plantapp.data.repository.PlantRepoImpl
import com.example.plantapp.domain.repository.PlantRepo
import com.example.plantapp.domain.use_case.category.GetCategoryUseCase
import com.example.plantapp.domain.use_case.question.GetQuestionUseCase
import com.example.plantapp.presentation.home.HomeViewModel
import com.example.plantapp.presentation.paywall.model.PayWallRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Hilt'e bu modülün uygulama seviyesinde geçerli olduğunu belirtiyoruz
object RepositoryModule {

    /** kullanacağımız bağımlılıkların tanımlanması */

    @Provides
    @Singleton
    fun providePlantRepo(api: PlantApi) : PlantRepo {
        return PlantRepoImpl(api)
    }

    @Provides
    @Singleton
    fun providePayWallRepository(
        @ApplicationContext context: Context
    ): PayWallRepository {
        Log.d("RepositoryModule", "providePayWallRepository")
        return PayWallRepository(context)
    }

    @Provides
    @Singleton
    fun provideGetCategoryUseCase(categoryRepository: PlantRepo): GetCategoryUseCase {
        return GetCategoryUseCase(categoryRepository)
    }

    @Provides
    @Singleton
    fun provideGetQuestionUseCase(questionRepository: PlantRepo): GetQuestionUseCase {
        return GetQuestionUseCase(questionRepository)
    }

    @Provides
    @Singleton
    fun provideHomeViewModel(
        getCategoryUseCase: GetCategoryUseCase,
        getQuestionUseCase: GetQuestionUseCase
    ): HomeViewModel {
        Log.d("AppModule", "homeViewModel")
        return HomeViewModel()
        //return HomeViewModel(getQuestionUseCase,getCategoryUseCase)
    }

}