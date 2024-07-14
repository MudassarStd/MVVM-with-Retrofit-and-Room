package com.android.solidmvvmarchitecture

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.android.solidmvvmarchitecture.application.QuoteApplication
import com.android.solidmvvmarchitecture.repository.Response
import com.android.solidmvvmarchitecture.viewmodel.QuoteViewModel
import com.android.solidmvvmarchitecture.viewmodel.QuoteViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var quoteViewModel: QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val quoteRepository = (application as QuoteApplication).quoteRepository
        quoteViewModel = ViewModelProvider(this, QuoteViewModelFactory(quoteRepository))[QuoteViewModel::class.java]

        quoteViewModel.quotes.observe(this) {
            when(it) {
                is Response.Loading -> Toast.makeText(this,"Loading data", Toast.LENGTH_SHORT).show()
                is Response.Success -> {
                    print("Handle success here")
                }
                is Response.Error -> Toast.makeText(this,"Loading data", Toast.LENGTH_SHORT).show()
            }
        }

    }
}