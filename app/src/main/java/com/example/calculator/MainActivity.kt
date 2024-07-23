package com.example.calculator

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val message = intent.getStringExtra("message")
        val textView: TextView = findViewById(R.id.textView)
        textView.text = message ?: "No message received"

        val num1EditText = findViewById<EditText>(R.id.num1EditText)
        val num2EditText = findViewById<EditText>(R.id.num2EditText)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val operatorSpinner = findViewById<Spinner>(R.id.operatorSpinner)

        val operators = arrayOf("addition", "subtraction", "multiplication", "division")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, operators)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        operatorSpinner.adapter = adapter

        calculateButton.setOnClickListener {
            val num1 = num1EditText.text.toString().toDoubleOrNull()
            val num2 = num2EditText.text.toString().toDoubleOrNull()
            val operator = operatorSpinner.selectedItem.toString()

            if (num1 == null || num2 == null) {
                resultTextView.text = "Please input right number"
            } else {
                val result = when (operator) {
                    "addition" -> num1 + num2
                    "subtraction" -> num1 - num2
                    "multiplication" -> num1 * num2
                    "division" -> {
                        if (num2 != 0.0) {
                            num1 / num2
                        } else {
                            "Wrong:number2 cannot be zero"
                        }
                    }

                    else -> "Wrong operation"
                }

                resultTextView.text = "The result: $result"

            }
        }
    }
}