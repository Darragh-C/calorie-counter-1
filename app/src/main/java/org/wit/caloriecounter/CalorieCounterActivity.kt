package org.wit.caloriecounter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.wit.caloriecounter.databinding.ActivityCaloriecounterBinding
import timber.log.Timber
import timber.log.Timber.Forest.i

class CalorieCounterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCaloriecounterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCaloriecounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())
        i("CalorieCounter started..")

        binding.btnAdd.setOnClickListener() {
            i("add Button Pressed")
        }
    }
}