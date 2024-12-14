package com.example.plantapp.presentation.paywall.model

import android.content.Context
import com.example.plantapp.R
import com.example.plantapp.data.dto.locale.User
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PayWallRepository @Inject constructor(
    @ApplicationContext private val context: Context // Hilt üzerinden context
) {
    fun getFeatures(): List<FeaturesModel> {
        return listOf(
            FeaturesModel(
                icon = R.drawable.baseline_document_scanner_24,
                type = context.getString(R.string.title_feature_1),
                description = context.getString(R.string.subtitle_feature_1)
            ),
            FeaturesModel(
                icon = R.drawable.faster,
                type = context.getString(R.string.title_feature_2),
                description = context.getString(R.string.subtitle_feature_2)
            ),
            FeaturesModel(
                icon = R.drawable.baseline_details_24,
                type = context.getString(R.string.title_feature_3),
                description = context.getString(R.string.subtitle_feature_3)
            )
        )
    }

    fun getQuestions(user: User?): List<PremiumTypeModel> {
        val premiumType = if(user?.premiumOption == 0) {
            EnumPremiumType.MONTH
        } else {
            EnumPremiumType.YEAR
        }
        return listOf(
            PremiumTypeModel(
                date = context.getString(R.string.title_premium_type_1),
                description = context.getString(R.string.subtitle_premium_type_1),
                discount = "",
                isChecked = premiumType == EnumPremiumType.MONTH,
                type = premiumType
            ),
            PremiumTypeModel(
                date = context.getString(R.string.title_premium_type_2),
                description = context.getString(R.string.subtitle_premium_type_2),
                discount = context.getString(R.string.discount_premium_type_2),
                isChecked = premiumType == EnumPremiumType.YEAR,
                type = premiumType
            )
        )
    }
}
