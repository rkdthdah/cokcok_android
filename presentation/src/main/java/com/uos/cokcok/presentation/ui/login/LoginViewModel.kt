package com.uos.cokcok.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.uos.cokcok.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
): BaseViewModel() {

    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean> get() = _isLogin

    fun loadData() = viewModelScope.launch {
        sortList()
        // Update UI
    }

    suspend fun sortList() = withContext(Dispatchers.Default) {

        // Heavy work
    }

    fun kakaoLogin() = viewModelScope.launch(Dispatchers.IO) {

    }
}