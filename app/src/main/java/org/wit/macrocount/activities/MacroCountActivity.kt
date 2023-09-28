package org.wit.macrocount.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.wit.macrocount.databinding.ActivityMacrocountBinding

import timber.log.Timber
import timber.log.Timber.Forest.i

class MacroCountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMacrocountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMacrocountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())
        i("CalorieCounter started..")

        binding.btnAdd.setOnClickListener() {
            i("add Button Pressed")
        }
    }
}