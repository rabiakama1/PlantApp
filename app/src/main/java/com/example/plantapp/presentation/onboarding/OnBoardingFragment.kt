package com.example.plantapp.presentation.onboarding

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.plantapp.databinding.FragmentOnBoardingBinding
import com.example.plantapp.presentation.onboarding.pages.FirstScreen
import com.example.plantapp.presentation.onboarding.pages.SecondScreen
import com.example.plantapp.presentation.onboarding.pages.adapter.ViewPagerAdapter

class OnBoardingFragment : Fragment() {

    private var _binding: FragmentOnBoardingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        val fragmentList = arrayListOf(
            FirstScreen(),
            SecondScreen()
        )
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        val viewPager = _binding?.viewPager
        viewPager?.adapter = adapter
        val indicator = _binding?.dotsIndicator
        // Seçili olan noktanın rengini yeşil yapar
        indicator?.selectedDotColor = Color.GREEN
        // Indicator viewpager a bağlanır ve kaydırıldığında noktalar da buna göre güncellenir
        viewPager?.let { indicator?.attachTo(it) }

        return _binding?.root
    }

}