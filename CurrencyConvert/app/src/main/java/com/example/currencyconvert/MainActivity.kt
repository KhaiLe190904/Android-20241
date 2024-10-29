package com.example.currencyconvert

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var editTextAmount1: EditText
    private lateinit var editTextAmount2: EditText
    private lateinit var spinnerCurrency1: Spinner
    private lateinit var spinnerCurrency2: Spinner
    private var isUpdating = false
    private var exchangeRates: HashMap<String, Double> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize views
        editTextAmount1 = findViewById(R.id.editTextAmount1)
        editTextAmount2 = findViewById(R.id.editTextAmount2)
        spinnerCurrency1 = findViewById(R.id.spinnerCurrency1)
        spinnerCurrency2 = findViewById(R.id.spinnerCurrency2)

        // Initialize exchange rates
        initializeExchangeRates()

        // Set up Spinner
        val currencies = arrayOf("VND", "USD", "EUR", "CNY", "JPY", "KRW", "GBP")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, currencies)
        spinnerCurrency1.adapter = adapter
        spinnerCurrency2.adapter = adapter

        // Set up text change listeners
        editTextAmount1.addTextChangedListener(AmountTextWatcher(editTextAmount1))
        editTextAmount2.addTextChangedListener(AmountTextWatcher(editTextAmount2))

        // Set up spinner selection listeners
        spinnerCurrency1.onItemSelectedListener = CurrencySelectedListener()
        spinnerCurrency2.onItemSelectedListener = CurrencySelectedListener()
    }

    private fun initializeExchangeRates() {
        exchangeRates["VND"] = 1.0
        exchangeRates["USD"] = 1.0/25000
        exchangeRates["EUR"] = 1.0/27500
        exchangeRates["CNY"] = 1.0/3550
        exchangeRates["JPY"] = 1.0/165
        exchangeRates["KRW"] = 1.0/18
        exchangeRates["GBP"] = 1.0/32895
    }

    private fun convertCurrency(
        sourceEditText: EditText,
        targetEditText: EditText,
        sourceSpinner: Spinner,
        targetSpinner: Spinner
    ) {
        if (isUpdating) return
        isUpdating = true

        var sourceAmount = 0.0
        sourceAmount = try {
            sourceEditText.text.toString().toDouble()
        } catch (e: NumberFormatException) {
            0.0
        }

        val sourceCurrency = sourceSpinner.selectedItem.toString()
        val targetCurrency = targetSpinner.selectedItem.toString()

        val sourceRate = exchangeRates[sourceCurrency] ?: 1.0 // Use a default if null
        val targetRate = exchangeRates[targetCurrency] ?: 1.0

        val targetAmount = sourceAmount * (targetRate / sourceRate)
        targetEditText.setText(String.format("%.4f", targetAmount))

        isUpdating = false
    }

    private inner class AmountTextWatcher(private val editText: EditText) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (!isUpdating) {
                if (editText == editTextAmount1) {
                    convertCurrency(editTextAmount1, editTextAmount2, spinnerCurrency1, spinnerCurrency2)
                } else {
                    convertCurrency(editTextAmount2, editTextAmount1, spinnerCurrency2, spinnerCurrency1)
                }
            }
        }

        override fun afterTextChanged(s: Editable) {}
    }

    private inner class CurrencySelectedListener : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            if (!isUpdating) {
                convertCurrency(editTextAmount1, editTextAmount2, spinnerCurrency1, spinnerCurrency2)
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>) {}
    }
}
