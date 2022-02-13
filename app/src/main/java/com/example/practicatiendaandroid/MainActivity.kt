package com.example.practicatiendaandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val bottomNavigationView= findViewById<BottomNavigationView>(R.id.activity_main__bottom_navigation)
//        val fragment= findViewById<FragmentContainerView>(R.id.fragment_container1)
//        val navController= fragment.findNavController()
//        bottomNavigationView.setupWithNavController(navController)

    }
}