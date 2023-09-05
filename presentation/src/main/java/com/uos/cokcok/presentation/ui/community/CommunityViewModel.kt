package com.uos.cokcok.presentation.ui.community

import androidx.lifecycle.viewModelScope
import com.uos.cokcok.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    //private val usecase
): BaseViewModel() {
    fun loadData() = viewModelScope.launch {
        sortList()
        // Update UI
    }


    suspend fun sortList() = withContext(Dispatchers.Default) {
        // Heavy work
    }
}