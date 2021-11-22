package com.mvvm.mykotlinkoin_template

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BaseViewModelFactory<T>(val creator: () -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator() as T
    }
}

/*ViewModelProviders.of(this,
 BaseViewModelFactory { UserViewModel(intent.getIntExtra(USER_ID, -1)) })
.get(UserViewModel::class.java)*/
