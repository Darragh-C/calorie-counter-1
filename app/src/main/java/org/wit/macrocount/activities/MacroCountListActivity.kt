package org.wit.macrocount.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.wit.macrocount.databinding.ActivityMacrocountListBinding
import org.wit.macrocount.databinding.CardMacrocountBinding
import org.wit.macrocount.main.MainApp
import org.wit.macrocount.models.MacroCountModel

class MacroCountListActivity : AppCompatActivity() {
    lateinit var app: MainApp
    private lateinit var binding: ActivityMacrocountListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMacrocountListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = MacroCountAdapter(app.macroCounts)
    }



}

class MacroCountAdapter constructor(private var macroCounts: List<MacroCountModel>):

    RecyclerView.Adapter<MacroCountAdapter.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardMacrocountBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val macroCount = macroCounts[holder.adapterPosition]
        holder.bind(macroCount)
    }

    override fun getItemCount(): Int = macroCounts.size

    class MainHolder(private val binding : CardMacrocountBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(macroCount: MacroCountModel) {
            binding.macroCounterTitle.text = macroCount.title
            binding.description.text = macroCount.description
        }
    }
}
