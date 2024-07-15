package com.example.expensetracker

import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val amount: Double,
    val description: String
): Serializable



class TransactionViewModel : ViewModel() {
    private val _transactions = MutableLiveData<List<Transaction>>()
    val transactions: LiveData<List<Transaction>>
        get() = _transactions
    init {
        _transactions.value = listOf()
    }

    fun saveTransaction(transaction: Transaction) {
        // Implement the logic to save the transaction
        // For example, add it to a list and update the LiveData
        val currentList = _transactions.value ?: emptyList()
        _transactions.value = currentList + transaction
    }
    fun getTransactionById(id: Int): LiveData<Transaction?>{
        val transaction = _transactions.value?.find { it.id == id }
        val liveData = MutableLiveData<Transaction?>()
        liveData.value = transaction
        return liveData
    }
}
