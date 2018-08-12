package com.manwinder.thetransformationcompany

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TransformersAdapter(private val transformersList: ArrayList<Transformer>) : RecyclerView.Adapter<TransformersAdapter.TransformerHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (transformersList[position].transformerType == TransformerType.AUTOBOT) {
            0
        } else {
            1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransformerHolder {

        val inflatedView = if (viewType == 0) {
            parent.inflate(R.layout.autobots_list_item)
        } else {
            parent.inflate(R.layout.decepticons_list_item)
        }

        return TransformerHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return transformersList.size
    }

    override fun onBindViewHolder(holder: TransformerHolder, position: Int) {
        holder.bind(transformersList[position])
    }

    class TransformerHolder(private val v: View): RecyclerView.ViewHolder(v) {
        fun bind(transformerItem: Transformer){
            v.findViewById<TextView>(R.id.transformer_name_tv).text = transformerItem.name
            v.findViewById<TextView>(R.id.strength_tv).text = "Strength: ${transformerItem.strength.toString()}"
            v.findViewById<TextView>(R.id.intelligence_tv).text = "Intelligence: ${transformerItem.intelligence.toString()}"
            v.findViewById<TextView>(R.id.speed_tv).text = "Speed: ${transformerItem.speed.toString()}"
            v.findViewById<TextView>(R.id.endurance_tv).text = "Endurance: ${transformerItem.endurance.toString()}"
            v.findViewById<TextView>(R.id.rank_tv).text = "Rank: ${transformerItem.rank.toString()}"
            v.findViewById<TextView>(R.id.courage_tv).text = "Courage: ${transformerItem.courage.toString()}"
            v.findViewById<TextView>(R.id.firepower_tv).text = "Firepower: ${transformerItem.firePower.toString()}"
            v.findViewById<TextView>(R.id.skill_tv).text = "Skill: ${transformerItem.skill.toString()}"
        }
    }
}