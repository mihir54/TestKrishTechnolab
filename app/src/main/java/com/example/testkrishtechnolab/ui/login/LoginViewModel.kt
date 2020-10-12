package com.example.testkrishtechnolab.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

import com.example.testkrishtechnolab.data.repository.LoginRepository
import com.example.testkrishtechnolab.ui.base.BaseViewModel
import com.example.testkrishtechnolab.utils.common.*
import com.example.testkrishtechnolab.utils.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import javax.net.ssl.HttpsURLConnection

class LoginViewModel @ViewModelInject constructor(
    private val loginRepository: LoginRepository,
    networkHelper: NetworkHelper,
    compositeDisposable: CompositeDisposable
) : BaseViewModel(compositeDisposable, networkHelper) {

    private val validationsList: MutableLiveData<List<Validation>> = MutableLiveData()
    val launchMain: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    val emailField: MutableLiveData<String> = MutableLiveData()
    val passwordField: MutableLiveData<String> = MutableLiveData()
    val loggingIn: MutableLiveData<Boolean> = MutableLiveData()

    val emailValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.EMAIL)
    val passwordValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.PASSWORD)

    private fun filterValidation(field: Validation.Field) =
        Transformations.map(validationsList) {
            it.find { validation -> validation.field == field }
                ?.run { return@run this.resource }
                ?: Resource.unknown()
        }

    fun onEmailChange(email: String) = emailField.postValue(email)

    fun onPasswordChange(email: String) = passwordField.postValue(email)

    fun onLogin() {
        val email = emailField.value
        val password = passwordField.value

        val validations = Validator.validateLoginFields(email, password)
        validationsList.postValue(validations)

        if (validations.isNotEmpty() && email != null && password != null) {
            val successValidation = validations.filter { it.resource.status == Status.SUCCESS }
            if (successValidation.size == validations.size && checkInternetConnectionWithMessage()) {
                loggingIn.postValue(true)
                compositeDisposable.add(
                    loginRepository.userLogin(email, password)
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                            { user ->
                                loginRepository.saveCurrentUser(user)
                                loggingIn.postValue(false)
                                launchMain.postValue(Event(emptyMap()))
                            },
                            { e ->
                                handleNetworkError(e)
                                loggingIn.postValue(false)
                            }
                        )
                )
            }
        }
    }

    override fun onCreate() {

    }

}