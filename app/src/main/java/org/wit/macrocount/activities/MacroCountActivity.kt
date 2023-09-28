package org.wit.macrocount.activities
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.wit.macrocount.databinding.ActivityMacrocountBinding
import org.wit.macrocount.models.MacroCountModel
import timber.log.Timber
import timber.log.Timber.Forest.i

class MacroCountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMacrocountBinding
    var macroCount = MacroCountModel()
    var macroCounts = ArrayList<MacroCountModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMacrocountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())
        i("MacroCount started..")

        binding.btnAdd.setOnClickListener() {
            macroCount.title = binding.macroCountTitle.text.toString()
            macroCount.description = binding.macroCountDescription.text.toString()
            if (macroCount.title.isEmpty()) {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
            else if (macroCount.description.isEmpty()) {
                Snackbar
                    .make(it,"Please Enter a description", Snackbar.LENGTH_LONG)
                    .show()
            }
            else {
                i("macroCount added: $macroCount")
                macroCounts.add(macroCount)
                i("Total MacroCounts: $macroCounts")
            }
        }
    }
}