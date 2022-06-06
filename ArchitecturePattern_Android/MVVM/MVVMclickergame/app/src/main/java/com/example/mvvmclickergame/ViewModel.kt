package com.example.mvvmclickergame

import androidx.lifecycle.MutableLiveData

class ViewModel {
    var result= MutableLiveData<Int>(0)
    fun onPlusClick(){
        result.value = result.value?.plus(1)
    }
    fun onMinusClick(){
        result.value=result.value?.minus(1)
    }
    fun onResetClick(){
        result.value=0
    }
}