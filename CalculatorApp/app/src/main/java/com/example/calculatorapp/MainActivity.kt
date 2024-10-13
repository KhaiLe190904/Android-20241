package com.example.calculatorapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var textReuslt: TextView

    var state: Int = 0
    var op: Int = 0
    var op1: Int = 0
    var op2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_linear_layout)
        textReuslt = findViewById(R.id.textView)

        findViewById<Button>(R.id.btn0).setOnClickListener(this)
        findViewById<Button>(R.id.btn1).setOnClickListener(this)
        findViewById<Button>(R.id.btn2).setOnClickListener(this)
        findViewById<Button>(R.id.btn3).setOnClickListener(this)
        findViewById<Button>(R.id.btn4).setOnClickListener(this)
        findViewById<Button>(R.id.btn5).setOnClickListener(this)
        findViewById<Button>(R.id.btn6).setOnClickListener(this)
        findViewById<Button>(R.id.btn7).setOnClickListener(this)
        findViewById<Button>(R.id.btn8).setOnClickListener(this)
        findViewById<Button>(R.id.btn9).setOnClickListener(this)
        findViewById<Button>(R.id.btnAdd).setOnClickListener(this)
        findViewById<Button>(R.id.btnSub).setOnClickListener(this)
        findViewById<Button>(R.id.btnMul).setOnClickListener(this)
        findViewById<Button>(R.id.btnDiv).setOnClickListener(this)
        findViewById<Button>(R.id.btnBS).setOnClickListener(this)
        findViewById<Button>(R.id.btnC).setOnClickListener(this)
        findViewById<Button>(R.id.btnCE).setOnClickListener(this)
//        findViewById<Button>(R.id.btnDot).setOnClickListener(this)
        findViewById<Button>(R.id.btnDiv).setOnClickListener(this)
        findViewById<Button>(R.id.btnEqual).setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val id = p0?.id
        if (id == R.id.btn0) {
            addDigit(0)
        } else if (id == R.id.btn1) {
            addDigit(1)
        } else if (id == R.id.btn2) {
            addDigit(2)
        } else if (id == R.id.btn3) {
            addDigit(3)
        } else if (id == R.id.btn4) {
            addDigit(4)
        } else if (id == R.id.btn5) {
            addDigit(5)
        } else if (id == R.id.btn6) {
            addDigit(6)
        } else if (id == R.id.btn7) {
            addDigit(7)
        } else if (id == R.id.btn8) {
            addDigit(8)
        } else if (id == R.id.btn9) {
            addDigit(9)
        } else if (id == R.id.btnC) {
            state = 0
            op1 = 0
            op2 = 0
            op = 0
            textReuslt.text = "0"
        } else if (id == R.id.btnBS) {
            if (state == 0) {
                if (op1 != 0) {
                    op1 = op1 / 10 // Xóa chữ số cuối cùng của op1
                    textReuslt.text = if (op1 != 0) "$op1" else "0"
                }
            } else {
                if (op2 != 0) {
                    op2 = op2 / 10 // Xóa chữ số cuối cùng của op2
                    textReuslt.text = if (op2 != 0) "$op2" else "0"
                }
            }
        }
        else if (id == R.id.btnAdd) {
            calculate()
            op = 1
            state = 1

        } else if (id == R.id.btnSub) {
            calculate()
            op = 2
            state = 1

        } else if (id == R.id.btnMul) {
            calculate()
            op = 3
            state = 1

        } else if (id == R.id.btnDiv) {
            calculate()
            op = 4
            state = 1

        } else if (id == R.id.btnEqual) {
            calculate()
            state = 0
            op = 0
        }
    }

    fun addDigit(c: Int) {
        if (state == 0) {
            op1 = op1 * 10 + c
            textReuslt.text = "$op1"
        } else {
            op2 = op2 * 10 + c
            textReuslt.text = "$op2"
        }
    }

    fun calculate() {
        if (op != 0) { // Nếu có phép toán cần thực hiện
            var result = 0
            when (op) {
                1 -> result = op1 + op2
                2 -> result = op1 - op2
                3 -> result = op1 * op2
                4 -> result = op1 / op2
            }
            op1 = result
            textReuslt.text = "$op1"
            op2 = 0
        }
    }
}
