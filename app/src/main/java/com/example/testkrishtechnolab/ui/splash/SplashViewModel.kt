package com.example.testkrishtechnolab.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.example.testkrishtechnolab.data.repository.LoginRepository
import com.example.testkrishtechnolab.ui.base.BaseViewModel
import com.example.testkrishtechnolab.utils.common.Event
import com.example.testkrishtechnolab.utils.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable



class SplashViewModel @ViewModelInject constructor(
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val loginRepository: LoginRepository
) : BaseViewModel(compositeDisposable, networkHelper) {

    val launchMain: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()
    val launchLogin: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    override fun onCreate() {
        if (loginRepository.getCurrentUser() != null)
            launchMain.postValue(Event(emptyMap()))
        else
            launchLogin.postValue(Event(emptyMap()))
    }
}