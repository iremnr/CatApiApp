package com.example.catapiapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapiapp.model.NewCatModel
import com.example.catapiapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ObserveViewModel @Inject constructor(private val repository: ObserveRepository) : ViewModel() {

    val cat: MutableLiveData<NewCatModel?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val onError: MutableLiveData<String?> = MutableLiveData()

    fun getData() = viewModelScope.launch { //fonksiyon asenkron çalışacağı için viewmodelscope
        isLoading.value = true

        when (val request = repository.getData()) { //istek oluşturuldu
            is NetworkResult.Success -> { //istek başarılı olduğunda
                cat.value = request.data
                isLoading.value = false
            }
            is NetworkResult.Error -> { //result hatalı geldiyse
                //onError.value = request.message
                isLoading.value = false
            }
        }
    }
}