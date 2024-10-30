package com.example.simplelist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var inputNumber: EditText
    private lateinit var radioEven: RadioButton
    private lateinit var radioOdd: RadioButton
    private lateinit var radioSquare: RadioButton
    private lateinit var buttonShow: Button
    private lateinit var listViewResult: ListView
    private lateinit var textViewError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        inputNumber = findViewById(R.id.inputNumber)
        radioOdd = findViewById(R.id.sole)
        radioEven = findViewById(R.id.sochan)
        radioSquare = findViewById(R.id.sochinhphuong)
        buttonShow = findViewById(R.id.buttonShow)
        listViewResult = findViewById(R.id.listViewResult)
        textViewError = findViewById(R.id.textViewError)

        buttonShow.setOnClickListener{
            showNumber()
        }
    }
    private fun showNumber(){
        val input = inputNumber.text.toString()
        textViewError.text = ""
        if(input.isEmpty()){
            textViewError.text = "Please type the number!"
            return
        }
        val n = input.toIntOrNull() ?: 0
        if(n == null || n <= 0){
            textViewError.text = "Hãy nhập số nguyên!"
        }
        val resultList = mutableListOf<Int>()
        when {
            radioEven.isChecked ->{
                for(i in 0..n step 2) resultList.add(i)
            }
            radioOdd.isChecked ->{
                for(i in 1..n step 2) resultList.add(i)
            }
            radioSquare.isChecked ->{
                var i = 0
                while (i*i <= n){
                    resultList.add(i*i)
                    i++
                }
            }
            else ->{
                textViewError.text = "Vui lòng chọn loại số."
            }
        }
        val  adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
        listViewResult.adapter = adapter
    }
}