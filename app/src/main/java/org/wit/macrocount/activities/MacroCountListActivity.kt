package org.wit.macrocount.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.wit.macrocount.R
import org.wit.macrocount.main.MainApp

class MacroCountListActivity : AppCompatActivity() {
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_macrocount_list)
        app = application as MainApp
    }
}