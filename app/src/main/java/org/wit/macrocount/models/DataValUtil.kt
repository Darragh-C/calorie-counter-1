package org.wit.macrocount.models
import timber.log.Timber.Forest.i
object DataValUtil {

    fun validNum(string: String): Boolean {
        i("Validating number")
        return string.all {
            it.isDigit()
        }
    }
}