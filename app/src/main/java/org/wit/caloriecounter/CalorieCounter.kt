package org.wit.caloriecounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber
import timber.log.Timber.Forest.i

class CalorieCounter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caloriecounter)

        Timber.plant(Timber.DebugTree())
        i("CalorieCounter started..")
    }
}