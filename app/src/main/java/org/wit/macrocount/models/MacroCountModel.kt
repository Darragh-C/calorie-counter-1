package org.wit.macrocount.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class MacroCountModel(var title: String = "",
                           var description: String = "",
                           var calories: String = "",
                           var protein: String = "",
                           var carbs: String = "",
                           var fat: String = "",) : Parcelable
