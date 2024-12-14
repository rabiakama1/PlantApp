package com.example.plantapp.presentation.paywall

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantapp.data.dto.locale.AppDatabase
import com.example.plantapp.data.dto.locale.User
import com.example.plantapp.data.dto.locale.UserDao
import com.example.plantapp.databinding.FragmentPayWallBinding
import com.example.plantapp.presentation.paywall.adapter.FeaturesRecyclerAdapter
import com.example.plantapp.presentation.paywall.adapter.PremiumOptionsRecyclerAdapter
import com.example.plantapp.presentation.paywall.model.EnumPremiumType
import com.example.plantapp.presentation.paywall.model.PayWallRepository
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint  // Hilt'in bu Fragment'te aktif olması için
class PayWallFragment : Fragment() {

    private lateinit var viewModel: PayWallViewModel
    private var _binding: FragmentPayWallBinding? = null
    private val binding get() = _binding
    private lateinit var featuresRecyclerAdapter: FeaturesRecyclerAdapter
    private lateinit var premiumOptionsRecyclerAdapter: PremiumOptionsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDatabase.getDatabase(requireContext())
        val userDao = db.userDao()
        val factory = PayWallViewModelFactory(userDao,PayWallRepository(requireContext()))
        viewModel = ViewModelProvider(this, factory)[PayWallViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPayWallBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * adapterlere recyclerViewların bağlanma işlemi
         * listenin yatay düzende olması için layoutmanager a horizontal verildi
         */
        featuresRecyclerAdapter = FeaturesRecyclerAdapter(emptyList())
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding?.rvFeatures?.layoutManager = linearLayoutManager
        binding?.rvFeatures?.adapter = featuresRecyclerAdapter

        premiumOptionsRecyclerAdapter = PremiumOptionsRecyclerAdapter(emptyList()) {
            viewModel.selectedOption = it
        }
        binding?.rvDateTypes?.layoutManager = LinearLayoutManager(context)
        binding?.rvDateTypes?.adapter = premiumOptionsRecyclerAdapter
        /**
         * viewmodelden alınan livedata verileri observe olduğunda adapterlerin doldurulması sağlanır
         */
        viewModel.features.observe(viewLifecycleOwner, Observer {
            it?.let {
                featuresRecyclerAdapter.defaultSetNewItemList(it)
            }
        })

        viewModel.premiumOptions.observe(viewLifecycleOwner, Observer {
            it?.let {
                premiumOptionsRecyclerAdapter.defaultSetNewItemList(it)
            }
        })

        /**
         * close ve try free butonlarına tıklandığında ekran yönlendirmesi yapılır
         */
        binding?.btnClose?.setOnClickListener {
            val action = PayWallFragmentDirections.actionPayWallFragmentToHomeFragment()
            Navigation.findNavController(it).navigate(action)
        }

        binding?.btnTryFree?.setOnClickListener {
            val action = PayWallFragmentDirections.actionPayWallFragmentToHomeFragment()
            Navigation.findNavController(it).navigate(action)
            // seçilen premium seçeneği ile bilgileri room a kaydet
            if(viewModel.isTryForFree().not()) {
                viewModel.insertUser()
            }
        }

        /**
         * kullanıcı için işlem yapılmak istenirse
         */
        viewModel.getUser().observe(viewLifecycleOwner, Observer { user ->
            if(user?.premiumOption == EnumPremiumType.MONTH.ordinal) {
                viewModel.selectedOption?.type = EnumPremiumType.MONTH
            } else {
                viewModel.selectedOption?.type = EnumPremiumType.YEAR
            }
        })
    }

}