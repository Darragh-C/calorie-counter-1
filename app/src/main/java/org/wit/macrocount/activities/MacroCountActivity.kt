package org.wit.macrocount.activities
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.wit.macrocount.R
import org.wit.macrocount.databinding.ActivityMacrocountBinding
import org.wit.macrocount.main.MainApp
import org.wit.macrocount.models.MacroCountModel
import timber.log.Timber.Forest.i

class MacroCountActivity : AppCompatActivity() {

    lateinit var app : MainApp
    private lateinit var binding: ActivityMacrocountBinding
    var macroCount = MacroCountModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMacrocountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)



        app = application as MainApp

        i("MacroCount started..")

        if (intent.hasExtra("macrocount_edit")) {
            macroCount = intent.extras?.getParcelable("macrocount_edit")!!
            binding.macroCountTitle.setText(macroCount.title)
            binding.macroCountDescription.setText(macroCount.description)
            binding.macroCountCalories.setText(macroCount.calories)
            binding.macroCountCarbs.setText(macroCount.carbs)
            binding.macroCountProtein.setText(macroCount.protein)
            binding.macroCountFat.setText(macroCount.fat)
        }

        binding.btnAdd.setOnClickListener() {
            macroCount.title = binding.macroCountTitle.text.toString()
            macroCount.description = binding.macroCountDescription.text.toString()
            macroCount.calories = binding.macroCountCalories.text.toString()
            macroCount.carbs = binding.macroCountCarbs.text.toString()
            macroCount.protein = binding.macroCountProtein.text.toString()
            macroCount.fat = binding.macroCountFat.text.toString()

            if (macroCount.title.isEmpty()) {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
            else {
                i("macroCount added: $macroCount.title")
                app.macroCounts.create(macroCount.copy())
                i("Total MacroCounts: ")
                for (i in app.macroCounts.findAll().indices)
                {
                    i("MacroCount[$i]:${app.macroCounts.findAll()[i]}")
                    setResult(RESULT_OK)
                    finish()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_macrocount, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}