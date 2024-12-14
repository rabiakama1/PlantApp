package com.example.plantapp.presentation.home

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantapp.domain.use_case.category.GetCategoryUseCase
import com.example.plantapp.domain.use_case.question.GetQuestionUseCase
import com.example.plantapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    //private val questionUseCase: GetQuestionUseCase,
    //private val categoryUseCase: GetCategoryUseCase
) : ViewModel() {

    private val _state = mutableStateOf<HomePageState>(HomePageState())
    val state : State<HomePageState> = _state
    private var job: Job? = null

    /**
     * Hilt ile viewmodel bağlantısını kurmada yaşadığım sorundan dolayı viewmodelin instanceını create etmede hata alıyorum
     * Ekranın açılabilmesi adına kodları yorum satırına aldım.
     * Provide kısmında bir problem oluyor ve Viewmodelin instanceı oluşmuyor bu sebeple de const. verilerine erişemiyor
     * Api isteği çalışmamış oluyor
     */

    init {
        /**
         * launch blokları birbirinden bağımsız çalışır işler birbirini kesmeden tamamlanır
         */
        viewModelScope.launch {
            launch { getPlantCategories(_state.value.searchVal) }
            launch { getQuestions() }
        }
    }

    fun getPlantCategories(searchVal : String?) {
        // bir job varsa cancel et ve yenisine eşitle
        job?.cancel()
        /*job = categoryUseCase.getCategoryList(_state.value.searchVal).onEach {
            // her bilgi geldiğinde
            when(it) {
                is Resource.Error -> {
                    _state.value = HomePageState(errorMsg = it.message)
                }
                is Resource.Loading -> {
                    _state.value = HomePageState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = HomePageState(plantCategories = it.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope) // hangi scopeta çalışacağı
         */

    }

    private fun getQuestions() {
        job?.cancel()
        /*
        job = questionUseCase.getQuestions().onEach {
            when(it) {
                is Resource.Error -> {
                    _state.value = HomePageState(errorMsg = it.message)
                }
                is Resource.Loading -> {
                    _state.value = HomePageState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = HomePageState(questions = it.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
         */

    }

    /** searchbarda search yapılmak istenirse tetiklenir*/
    fun onEvent(e: HomePageEvent) {
        when(e) {
            is HomePageEvent.SearchPlant -> {
                getPlantCategories(e.searchVal)
            }
            else -> {}
        }
    }

}