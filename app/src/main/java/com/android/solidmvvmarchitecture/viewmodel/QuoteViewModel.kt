package com.android.solidmvvmarchitecture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.solidmvvmarchitecture.models.Quotes
import com.android.solidmvvmarchitecture.repository.QuoteRepository
import com.android.solidmvvmarchitecture.repository.Response
import kotlinx.coroutines.launch

class QuoteViewModel(val repository: QuoteRepository) : ViewModel() {


    init {
            viewModelScope.launch {
                repository.getQuotes(1)
            }
    }

    val quotes : LiveData<Response<Quotes>> get() = repository.quotes

}