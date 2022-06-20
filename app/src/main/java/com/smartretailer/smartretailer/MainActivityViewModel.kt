package com.smartretailer.smartretailer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartretailer.smartretailer.helpers.Singedinuser
import com.smartretailer.smartretailer.repository.Repository


class MainActivityViewModel: ViewModel() {
    var triggercount=MutableLiveData<Boolean>()
val repo=Repository()
    fun triggercount(){
        repo.triggercount.observeForever{
            triggercount.value=true
        }
    }

    fun refreshtoken() {
        repo.refreshtoken(Singedinuser.refreshtoken)
    }
}