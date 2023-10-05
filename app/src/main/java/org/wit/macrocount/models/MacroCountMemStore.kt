package org.wit.macrocount.models
import timber.log.Timber.Forest.i
class MacroCountMemStore: MacroCountStore {

    val macroCounts = ArrayList<MacroCountModel>()

    override fun findAll(): List<MacroCountModel> {
        return macroCounts
    }

    override fun create(macroCount: MacroCountModel) {
        macroCounts.add(macroCount)
    }

    fun logAll() {
        macroCounts.forEach{ i("${it}")}
    }
}