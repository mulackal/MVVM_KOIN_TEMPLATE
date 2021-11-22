package com.mvvm.mykotlinkoin_template.ui.main.viewmodel

import androidx.lifecycle.*
import com.eduvy.demo.base.BaseViewModel
import com.mvvm.mykotlinkoin_template.data.model.User
import com.mvvm.mykotlinkoin_template.data.preferences.SharedPreferenceUtils
import com.mvvm.mykotlinkoin_template.data.preferences.SharedPreferenceValue
import com.mvvm.mykotlinkoin_template.data.repository.MainRepository
import com.mvvm.mykotlinkoin_template.utils.NetworkHelper
import com.mvvm.mykotlinkoin_template.utils.Resource

class MainViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper,
    private val preferences: SharedPreferenceUtils
) : BaseViewModel() {

    private val _users = MutableLiveData<Resource<List<User>>>()
    val users: LiveData<Resource<List<User>>> get() = _users

    init {
        fetchUsers()
        preferences.setValue(SharedPreferenceValue.USER_NAME,"VIPIN")
    }

    private fun fetchUsers() {
        launch {
            _users.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getUsers().let {
                    if (it.isSuccessful) {
                        _users.postValue(Resource.success(it.body()))
                    } else _users.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _users.postValue(Resource.error("No internet connection", null))
        }
    }
}