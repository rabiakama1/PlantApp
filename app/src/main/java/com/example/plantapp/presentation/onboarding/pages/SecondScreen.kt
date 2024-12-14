package com.example.plantapp.presentation.onboarding.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.plantapp.R

class SecondScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_second_screen, container, false)

        val next = view.findViewById<TextView>(R.id.btn_continue)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)

        next.setOnClickListener {
            val totalScreens = viewPager?.adapter?.itemCount ?: 0
            val currentScreen = viewPager?.currentItem
            if (currentScreen != null) {
                if (currentScreen < totalScreens - 1) {
                    // sonraki onboard ekranını aç
                    viewPager.currentItem = currentScreen + 1
                } else {
                    // onboard bittiyse paywall ekranını aç
                    findNavController().navigate(R.id.action_onBoardingFragment_to_payWallFragment)
                }
            }
        }
        return view
    }

}