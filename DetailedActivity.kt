package com.example.expensetracker

import android.os.Bundle
import android.text.Editable
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.expensetracker.databinding.ActivityDetailedBinding

class DetailedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailedBinding
    private val viewModel: TransactionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the transaction ID from the intent
        val transactionId = intent.getIntExtra("TRANSACTION_ID", -1)
        if (transactionId != -1) {
            // Load the transaction details
            viewModel.getTransactionById(transactionId).observe(this) { transaction ->
                transaction?.let {
                    displayTransactionDetails(it)
                }
            }
        }
    }

    private fun displayTransactionDetails(transaction: Transaction) {
        binding.amountInput.text = Editable.Factory.getInstance().newEditable(transaction.amount.toString())
        binding.descriptionInput.text = Editable.Factory.getInstance().newEditable(transaction.description)

    }
}
