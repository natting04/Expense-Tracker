package com.example.expensetracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TransactionAdapter(
    private val transactions: List<Transaction>,
    private val onTransactionClick: (Int) -> Unit
) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(itemView: View, val onTransactionClick: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val amountInput: TextView = itemView.findViewById(R.id.amountInput)
        private val descriptionInput: TextView = itemView.findViewById(R.id.descriptionInput)

        fun bind(transaction: Transaction) {
            amountInput.text = transaction.amount.toString()
            descriptionInput.text = transaction.description

            itemView.setOnClickListener {
                onTransactionClick(transaction.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaction_layout, parent, false)
        return TransactionViewHolder(view, onTransactionClick)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactions[position])
    }

    override fun getItemCount() = transactions.size
}
