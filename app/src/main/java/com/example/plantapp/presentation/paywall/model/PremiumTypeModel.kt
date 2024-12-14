package com.example.plantapp.presentation.paywall.model

data class PremiumTypeModel(
    val date: String? = "",
    val description: String? = "",
    val discount: String? = "",
    var isChecked: Boolean? = false,
    var type: EnumPremiumType
)

enum class EnumPremiumType {
    MONTH,
    YEAR
}