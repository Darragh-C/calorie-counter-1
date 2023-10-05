package org.wit.macrocount.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.wit.macrocount.R
import org.wit.macrocount.databinding.ActivityMacrocountListBinding
import org.wit.macrocount.databinding.CardMacrocountBinding
import org.wit.macrocount.main.MainApp
import org.wit.macrocount.models.MacroCountModel

class MacroCountListActivity : AppCompatActivity(), MacroCountListener {
    lateinit var app: MainApp
    private lateinit var binding: ActivityMacrocountListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMacrocountListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = MacroCountAdapter(app.macroCounts.findAll(), this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, MacroCountActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.macroCounts.findAll().size)
            }
        }

    override fun onMacroCountClick(macroCount: MacroCountModel) {
        val launcherIntent = Intent(this, MacroCountActivity::class.java)
        launcherIntent.putExtra("macrocount_edit", macroCount)
        getClickResult.launch(launcherIntent)
    }

    private val getClickResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.macroCounts.findAll().size)
            }
        }
}

interface MacroCountListener{
    fun onMacroCountClick(macroCount: MacroCountModel)
}

class MacroCountAdapter constructor(private var macroCounts: List<MacroCountModel>,
                                    private val listener: MacroCountListener):

    RecyclerView.Adapter<MacroCountAdapter.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardMacrocountBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val macroCount = macroCounts[holder.adapterPosition]
        holder.bind(macroCount, listener)
    }

    override fun getItemCount(): Int = macroCounts.size

    class MainHolder(private val binding : CardMacrocountBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(macroCount: MacroCountModel, listener: MacroCountListener) {
            binding.macroCounterTitle.text = macroCount.title
            binding.description.text = macroCount.description
            binding.root.setOnClickListener { listener.onMacroCountClick(macroCount) }

        }
    }
}
