package com.example.plantapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantapp.R
import com.example.plantapp.data.dto.remote.PlantApi
import com.example.plantapp.data.repository.PlantRepoImpl
import com.example.plantapp.databinding.FragmentHomeBinding
import com.example.plantapp.domain.repository.PlantRepo
import com.example.plantapp.domain.use_case.category.GetCategoryUseCase
import com.example.plantapp.domain.use_case.category.GetCategoryUseCase_Factory
import com.example.plantapp.domain.use_case.question.GetQuestionUseCase
import com.example.plantapp.presentation.home.adapter.category.PlantCategoriesRecyclerAdapter
import com.example.plantapp.presentation.home.adapter.question.PlantQuestionsRecyclerAdapter
import com.example.plantapp.presentation.paywall.PayWallViewModel
import com.example.plantapp.presentation.paywall.PayWallViewModelFactory
import com.example.plantapp.presentation.paywall.model.PayWallRepository
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint  // Hilt'in bu Fragment'te aktif olması için
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels() // Hilt ile ViewModel bağlama
    //hiltNavGraphViewModels(R.id.homeFragment)
    private var _binding: FragmentHomeBinding? = null // xml e erişim için
    private val binding get() = _binding
    private lateinit var plantCategoriesAdapter: PlantCategoriesRecyclerAdapter
    private lateinit var questionsAdapter: PlantQuestionsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val factory = HomeViewModelFactory(PlantRepoImpl(PlantApi::class.java))
        //viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * adapterlere recyclerViewların bağlanma işlemi
         * gridlayout kullanarak listelerin yatayda verilen count kadar grid görünümünde olması sağlandı
         */
        plantCategoriesAdapter = PlantCategoriesRecyclerAdapter(emptyList())
        val gridLayoutManagerC = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
        binding?.rvCategory?.layoutManager = gridLayoutManagerC
        binding?.rvCategory?.adapter = plantCategoriesAdapter

        questionsAdapter = PlantQuestionsRecyclerAdapter(emptyList())
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding?.rvQuestion?.layoutManager = linearLayoutManager
        binding?.rvQuestion?.adapter = questionsAdapter

        /** viewmodelden api verileri alınır ve adapter doldurulur */
        viewModel.getPlantCategories("")
        val state = viewModel.state.value
        plantCategoriesAdapter = PlantCategoriesRecyclerAdapter(state.plantCategories)
        questionsAdapter = PlantQuestionsRecyclerAdapter(state.questions)

        // Loading veya hata durumu
        if (state.isLoading) {
            // Loading indicator'ı göster
        }
        state.errorMsg?.let {
            // Hata mesajını göster
            Toast.makeText(this.context,it,Toast.LENGTH_SHORT)
        }

        binding?.tvTime.let {
           it?.text = getTimeTitle()
        }
        binding?.searchView.let {
            it?.queryHint = it?.context?.getString(R.string.title_search)
        }
    }

    private fun getTimeTitle() : String? {
        //current saat alınır
        val dateFormat = SimpleDateFormat("HH", Locale.getDefault())
        val currentHour = dateFormat.format(Date()).toInt()
        return when (currentHour) {
            in 6..12 -> {  // Saat 6 ile 12 arasında
                context?.getString(R.string.title_morning)
            }
            in 12..17 -> {  // Saat 12 ile 17 arasında
                context?.getString(R.string.title_afternoon)
            }
            in 17..23 -> {  // Saat 17 ile 23 arasında
                context?.getString(R.string.title_evening)
            }
            else -> {
                context?.getString(R.string.title_night)
            }
        }
    }
}