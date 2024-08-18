package com.practice.calculator

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.practice.calculator.databinding.ActivityMainBinding
import com.practice.calculator.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            loadFragment(HomeFragment.getInstance())
        }catch (e: Exception){
            e.errorLogs()
        }

    }

    private fun loadFragment(fragment: Fragment){
        try {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(
                binding.flContainerForFragmentsFragmentHost.id,
                fragment
            )
            fragmentTransaction.commit()
        }catch (e: Exception){
            e.errorLogs()
        }
    }

    fun getActualScaledPixels(resourceId: Int):Float{
        val metrics: DisplayMetrics = resources.displayMetrics
        val density = metrics.density
        return getResources().getDimension(resourceId) / density
    }
}