package org.wit.macrocount.main

import android.app.Application
import org.wit.macrocount.models.MacroCountModel
import timber.log.Timber
import timber.log.Timber.Forest.i

class MainApp : Application() {
    var macroCounts = ArrayList<MacroCountModel>()
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("MacroCount started")
    }
}