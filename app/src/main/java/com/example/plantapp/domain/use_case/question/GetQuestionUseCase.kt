package com.example.plantapp.domain.use_case.question

import com.example.plantapp.data.dto.remote.dto.question.QuestionDto
import com.example.plantapp.data.dto.remote.dto.question.QuestionDtoItem
import com.example.plantapp.domain.repository.PlantRepo
import com.example.plantapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetQuestionUseCase @Inject constructor(private val repo: PlantRepo){

    fun getQuestions() : Flow<Resource<List<QuestionDtoItem>>> = flow {
       try {
           emit(Resource.Loading())
           val questionList = repo.getQuestions()
           emit(Resource.Success(questionList.toList()))
       } catch (e: Exception) {
           emit(Resource.Error(message = e.localizedMessage ?: "error"))
       }
    }
}