package com.example.expensetracker

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: TransactionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up RecyclerView with the adapter
        setupTransactionsAdapter()

        // Example: Add a new transaction (this would typically be triggered by user input)
        binding.addBtn.setOnClickListener {
            val newTransaction = Transaction(
                id = (viewModel.transactions.value?.size ?: 0) + 1,
                amount = 100.0,
                description = "Sample Transaction"
            )
            viewModel.saveTransaction(newTransaction)
        }
    }

    private fun setupTransactionsAdapter() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        // Observe transactions LiveData
        viewModel.transactions.observe(this) { transactions ->
            val transactionAdapter = TransactionAdapter(transactions) { transactionId ->
                val intent = Intent(this, DetailedActivity::class.java)
                intent.putExtra("TRANSACTION_ID", transactionId)
                startActivity(intent)
            }
            binding.recyclerview.adapter = transactionAdapter
        }
    }
}
