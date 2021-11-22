package com.eduvy.demo.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*


abstract class BaseViewModel : ViewModel() {

    fun launch(blockScope: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(block = blockScope)

    fun async(blockScope: suspend CoroutineScope.() -> Unit) =
        viewModelScope.async(block = blockScope)


   /* open val isProgressBarVisible: ObservableBoolean = ObservableBoolean()
    open fun setIsProgressBarVisible(boolean: Boolean) {
        isProgressBarVisible.set(boolean) }*/

    var job: CompletableJob? = null
    fun cancelJobs(){
        if(job!=null)
            job?.cancel()
    }

}
