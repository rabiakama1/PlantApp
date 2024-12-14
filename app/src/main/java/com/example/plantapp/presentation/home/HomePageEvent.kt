package com.example.plantapp.presentation.home

sealed class HomePageEvent {
    data class SearchPlant(val searchVal : String): HomePageEvent()
}