package com.example.plantapp.domain.use_case.category

import com.example.plantapp.data.dto.remote.dto.category.CategoryItem
import com.example.plantapp.domain.repository.PlantRepo
import com.example.plantapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(private val repo: PlantRepo){

    fun getCategoryList(searchVal: String?) : Flow<Resource<List<CategoryItem>>> = flow {
        try {
            emit(Resource.Loading())
            val categoryList = repo.getCategories(searchVal)
            emit(Resource.Success(categoryList.data.toList()))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "error"))
        }
    }
}