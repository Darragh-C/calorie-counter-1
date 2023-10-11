package org.wit.macrocount.activities
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.wit.macrocount.R
import org.wit.macrocount.databinding.ActivityMacrocountBinding
import org.wit.macrocount.main.MainApp
import org.wit.macrocount.models.DataValUtil
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
            binding.btnAdd.setText(R.string.save_macroCount)
        }

        binding.btnAdd.setOnClickListener() {
            macroCount.title = binding.macroCountTitle.text.toString()
            macroCount.description = binding.macroCountDescription.text.toString()

            val carbsInput = binding.macroCountCarbs.text.toString()
            val proteinInput = binding.macroCountProtein.text.toString()
            val fatInput = binding.macroCountFat.text.toString()
            val caloriesInput = binding.macroCountCalories.text.toString()


            var validationFailed = false

            if (macroCount.title.isEmpty()) {
                Snackbar
                    .make(it, R.string.snackbar_macroCountTitle, Snackbar.LENGTH_LONG)
                    .show()
                validationFailed = true
            }

            if (caloriesInput.isNotEmpty() && !DataValUtil.validNum(caloriesInput)) {
                Snackbar
                    .make(it, R.string.snackbar_macroCountCalories, Snackbar.LENGTH_LONG)
                    .show()
                validationFailed = true
            }

            if (carbsInput.isNotEmpty() && !DataValUtil.validNum(carbsInput)) {
                Snackbar
                    .make(it, R.string.snackbar_macroCountCarbs, Snackbar.LENGTH_LONG)
                    .show()
                validationFailed = true
            }

            if (proteinInput.isNotEmpty() && !DataValUtil.validNum(proteinInput)) {
                Snackbar
                    .make(it, R.string.snackbar_macroCountProtein, Snackbar.LENGTH_LONG)
                    .show()
                validationFailed = true
            }

            if (fatInput.isNotEmpty() && !DataValUtil.validNum(fatInput)) {
                Snackbar
                    .make(it, R.string.snackbar_macroCountFat, Snackbar.LENGTH_LONG)
                    .show()
                validationFailed = true
            }

            if (!validationFailed) {
                i("macroCount added: $macroCount.title")
                app.macroCounts.create(macroCount.copy())
                i("Total MacroCounts: ")
                for (i in app.macroCounts.findAll().indices) {
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