package com.example.plantapp.presentation.paywall

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.plantapp.data.dto.locale.User
import com.example.plantapp.data.dto.locale.UserDao
import com.example.plantapp.presentation.paywall.model.EnumPremiumType
import com.example.plantapp.presentation.paywall.model.FeaturesModel
import com.example.plantapp.presentation.paywall.model.PayWallRepository
import com.example.plantapp.presentation.paywall.model.PremiumTypeModel
import com.example.plantapp.presentation.paywall.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PayWallViewModel @Inject constructor(
    private val userDao: UserDao,
    private val payWallRepository: PayWallRepository
) : ViewModel() {

    var selectedOption: PremiumTypeModel? = null

    private val _features = MutableLiveData<List<FeaturesModel>>()
    val features : LiveData<List<FeaturesModel>> = _features

    private val _userInfo = MutableLiveData<UserModel>()
    private val userInfo: LiveData<UserModel> = _userInfo

    /** listenin içeriği değiştiğinde otomatik olarak UI güncellemesi sağlar. */
    private val _premiumOptions = MutableLiveData<List<PremiumTypeModel>>()
    val premiumOptions : LiveData<List<PremiumTypeModel>> = _premiumOptions

    init {
        getAdapterValues()
    }

    private fun getAdapterValues() {
        viewModelScope.launch {
            _features.value = payWallRepository.getFeatures()
            userDao.getUserOption().run {
                _premiumOptions.value = payWallRepository.getQuestions(this)
                val u = UserModel(this?.date,this?.premiumOption)
                _userInfo.value = u
            }
        }
    }

    /**
     * Roomdan kullanıcı çek
     * Burdan gelen bilgiye göre UI düzenlemesi yapılabilir.
     */
    fun getUser() = liveData {
        val user = userDao.getUserOption()
        emit(user)
    }

    /**
     * Room a kullanıcı ekle
     */
    fun insertUser() {
        val paymentTotal = if(selectedOption?.type == EnumPremiumType.MONTH) {
            2.99
        } else 529.99
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val currentDate = dateFormat.format(Date())
        val newUser = User(date = currentDate, premiumOption = selectedOption?.type?.ordinal, payment = paymentTotal)
        viewModelScope.launch {
            userDao.insertUser(newUser)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isTryForFree(): Boolean {
        return try {
            // String'i date'e çevirir
            val parsedDate = LocalDateTime.parse(userInfo.value?.date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            val today = LocalDateTime.now()
            // Bugünden 3 gün öncesini almak için
            val threeDaysAgo = today.minusDays(3)
            // Tarih bugünden küçük eşit ve 3 gün öncesinden büyük eşit mi?
            !(parsedDate.isAfter(threeDaysAgo) || parsedDate.isEqual(threeDaysAgo))
        } catch (e: Exception) {
            true
        }
    }

}