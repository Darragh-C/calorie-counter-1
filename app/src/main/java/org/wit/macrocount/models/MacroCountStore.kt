package org.wit.macrocount.models

interface MacroCountStore {
    fun findAll(): List<MacroCountModel>
    fun create(placemark: MacroCountModel)
}