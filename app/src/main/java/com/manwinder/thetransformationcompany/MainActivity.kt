package com.manwinder.thetransformationcompany

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val autobotsList = ArrayList<Transformer>()
    private val decepticonsList = ArrayList<Transformer>()
    private val transformersList = ArrayList<Transformer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transformers_rv.addItemDecoration(DividerItemDecoration(transformers_rv.context, DividerItemDecoration.VERTICAL))

        transformers_rv.layoutManager = LinearLayoutManager(this)
        val transformersAdapter = TransformersAdapter(transformersList) {
            position -> run {
                transformersList.removeAt(position)
                transformers_rv.adapter?.notifyItemRemoved(position)
            }
        }
        transformers_rv.adapter = transformersAdapter

        fab.setOnClickListener {
            addTransformerDialog()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.fight_menu, menu)
        val fight = menu?.findItem(R.id.fight)
        fight?.setOnMenuItemClickListener {
            val fightObj = TransformersFight(autobotsList, decepticonsList)
            createAlertDialog(fightObj)
            true
        }
        return true
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
                    return@setOnClickListener
                }
            }

            val criteriaVals = ArrayList<Int>()
            for (i in 0 until spinners.size){
                criteriaVals.add(spinners[i].selectedItem.toString().toInt())
            }

            val checkedRadioButtonId = radioGroup?.checkedRadioButtonId

            val transformer = Transformer(transformerName?.text.toString(), criteriaVals[0], criteriaVals[1], criteriaVals[2], criteriaVals[3], criteriaVals[4], criteriaVals[5], criteriaVals[6], criteriaVals[7])
            transformer.setOverallRating()

            if (checkedRadioButtonId == R.id.autobot_rb) {
                transformer.transformerType = TransformerType.AUTOBOT
                autobotsList.add(transformer)
            } else {
                transformer.transformerType = TransformerType.DECEPTICON
                decepticonsList.add(transformer)
            }
            transformersList.add(transformer)

            transformers_rv.adapter?.notifyItemInserted(transformersList.size)
            dialog.dismiss()
        }
    }

    private fun createAlertDialog(fight: TransformersFight) {
        val builder: AlertDialog.Builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert)
        } else {
            AlertDialog.Builder(this)
        }

        builder.setTitle("Fight Results")
                .setMessage("""
                    ${fight.fightCount} battle${if (fight.fightCount > 1) "s" else ""}
                    Winning team (${fight.getWinningTeamName()}): ${fight.getWinningTeamMembers()}
                    Survivors from the losing team (${fight.getLosingTeamName()}): ${fight.getLosingTeamMembersThatSurvived()}
                """.trimIndent())
                .setPositiveButton(android.R.string.yes, null)
                .show()
    }
}
