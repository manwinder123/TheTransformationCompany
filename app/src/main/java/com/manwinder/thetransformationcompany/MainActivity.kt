package com.manwinder.thetransformationcompany

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transformers_rv.addItemDecoration(DividerItemDecoration(transformers_rv.context, DividerItemDecoration.VERTICAL))
        var transformersList = ArrayList<Transformer>()
        
        transformers_rv.layoutManager = LinearLayoutManager(this)
        transformers_rv.adapter = TransformersAdapter(transformersList)
    }
}
