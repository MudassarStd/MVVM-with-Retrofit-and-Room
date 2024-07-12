package com.android.solidmvvmarchitecture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.android.solidmvvmarchitecture.models.Quotes
import com.android.solidmvvmarchitecture.repository.QuoteRepository
import kotlinx.coroutines.launch

class QuoteViewModel(val repository: QuoteRepository) : ViewModel() {


    init {
            viewModelScope.launch {
                repository.getQuotes(1)
            }
    }

    val quotes : LiveData<Quotes> get() = repository.quotes


}