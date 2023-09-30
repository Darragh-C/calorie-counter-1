package org.wit.macrocount.activities
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.wit.macrocount.databinding.ActivityMacrocountBinding
import org.wit.macrocount.main.MainApp
import org.wit.macrocount.models.MacroCountModel
import timber.log.Timber.Forest.i

class MacroCountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMacrocountBinding
    var macroCount = MacroCountModel()
    lateinit var app : MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMacrocountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        i("MacroCount started..")

        binding.btnAdd.setOnClickListener() {
            macroCount.title = binding.macroCountTitle.text.toString()
            macroCount.description = binding.macroCountDescription.text.toString()

            if (macroCount.title.isEmpty()) {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
            else {
                i("macroCount added: $macroCount.title")
                app.macroCounts.add(macroCount.copy())
                i("Total MacroCounts: ")
                for (i in app.macroCounts.indices)
                {
                    i("MacroCount[$i]:${app.macroCounts[i]}")
                }
            }
        }
    }
}