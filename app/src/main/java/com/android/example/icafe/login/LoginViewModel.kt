package com.android.example.icafe.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {



    private val _eventCheckInput = MutableLiveData<Boolean>()
    val eventCheckInput : LiveData<Boolean>
        get() = _eventCheckInput

    val _toastEmptyInput= MutableLiveData<Boolean>()
    val toastEmptyInput  : LiveData<Boolean>
        get() = _toastEmptyInput

    val _toastShowMessage= MutableLiveData<Boolean>()
    val toastShowMessage  : LiveData<Boolean>
        get() = _toastShowMessage

    val _eventSuccessLogin= MutableLiveData<Boolean>()
    val eventSuccessLogin  : LiveData<Boolean>
        get() = _eventSuccessLogin


    init {
        Log.i("LoginViewModel", "LoginViewModel --> Created ")
        _eventCheckInput.value = false
        _toastEmptyInput.value = false
        _toastShowMessage.value = false
        _eventSuccessLogin.value = false
    }


    fun loginEnter(){
        Log.i("PerthLog","Clicked !!")
        _eventCheckInput.value = true
    }

    fun checkInput(username:String , password: String){
        if(username == "" || username == null || password == "" || password == null) {
            _toastEmptyInput.value = true
        }else{
            validateAdmin(username,password)
        }
    }

    fun validateAdmin(username : String , password: String){
        Log.i("LoginViewModel", "username: ${username}} / password: ${password}")

        if(username == "admin" && password == "12345"){
            _eventSuccessLogin.value = true
        }else{
            _toastShowMessage.value = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("LoginViewModel", "LoginViewModel --> destroyed")
    }

}