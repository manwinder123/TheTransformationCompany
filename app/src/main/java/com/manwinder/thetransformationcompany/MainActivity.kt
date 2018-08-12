package com.manwinder.thetransformationcompany

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val transformersList = ArrayList<Transformer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transformers_rv.addItemDecoration(DividerItemDecoration(transformers_rv.context, DividerItemDecoration.VERTICAL))

        transformers_rv.layoutManager = LinearLayoutManager(this)
        transformers_rv.adapter = TransformersAdapter(transformersList)

        fab.setOnClickListener {
            addTransformerDialog()
        }
    }

    private fun addTransformerDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.add_transformer, null)
        dialogBuilder.setView(dialogView)

        val dialog = dialogBuilder.create()
        dialog.show()

        val spinners = ArrayList<Spinner>()

        dialog.findViewById<Spinner>(R.id.strength_spinner)?.let { spinners.add(it) }
        dialog.findViewById<Spinner>(R.id.intelligence_spinner)?.let { spinners.add(it) }
        dialog.findViewById<Spinner>(R.id.speed_spinner)?.let { spinners.add(it) }
        dialog.findViewById<Spinner>(R.id.endurance_spinner)?.let { spinners.add(it) }
        dialog.findViewById<Spinner>(R.id.rank_spinner)?.let { spinners.add(it) }
        dialog.findViewById<Spinner>(R.id.courage_spinner)?.let { spinners.add(it) }
        dialog.findViewById<Spinner>(R.id.firepower_spinner)?.let { spinners.add(it) }
        dialog.findViewById<Spinner>(R.id.skill_spinner)?.let { spinners.add(it) }

        for (i in 0 until spinners.size){
            val adapter = ArrayAdapter.createFromResource(this, R.array.drop_down_selection, android.R.layout.simple_spinner_item)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
// Apply the adapter to the spinner
            spinners[i].adapter = adapter
        }

        val transformerName= dialog.findViewById<EditText>(R.id.transformer_name_et)
        val radioGroup = dialog.findViewById<RadioGroup>(R.id.transformer_rg)

        val addTransactionBtn= dialog.findViewById<Button>(R.id.add_btn)
        addTransactionBtn?.setOnClickListener { _ ->

            transformerName?.text?.let {
                if (it.isEmpty()){
                    transformerName.error = "Transformer name required"
                }
            }

            val criteriaVals = ArrayList<Int>()
            for (i in 0 until spinners.size){
                criteriaVals.add(spinners[i].selectedItem.toString().toInt())
            }

            val checkedRadioButtonId = radioGroup?.checkedRadioButtonId

            if (checkedRadioButtonId == R.id.autobot_rb) {
                transformersList.add(Transformer(TransformerType.AUTOBOT, transformerName?.text.toString(), criteriaVals[0], criteriaVals[1], criteriaVals[2], criteriaVals[3], criteriaVals[4], criteriaVals[5], criteriaVals[6], criteriaVals[7]))
            } else {
                transformersList.add(Transformer(TransformerType.DECEPTICON, transformerName?.text.toString(), criteriaVals[0], criteriaVals[1], criteriaVals[2], criteriaVals[3], criteriaVals[4], criteriaVals[5], criteriaVals[6], criteriaVals[7]))
            }
            transformers_rv.adapter?.notifyItemInserted(transformersList.size)
            dialog.dismiss()
        }
    }
}
