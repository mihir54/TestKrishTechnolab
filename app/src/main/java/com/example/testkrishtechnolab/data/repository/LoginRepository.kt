package com.example.testkrishtechnolab.data.repository

import com.example.testkrishtechnolab.data.model.Customer
import com.example.testkrishtechnolab.data.prefs.CustomerPreferences
import com.example.testkrishtechnolab.data.remote.ApiHelper
import io.reactivex.Single

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val customerPreferences: CustomerPreferences
    ) {

    fun userLogin(email : String, password : String) : Single<Customer> =
        apiHelper.loginCall(email, password)
            .map { user ->
                Customer(
                    user.userId,
                    user.firstname,
                    user.email,
                    user.customerToken
                )
            }

    fun saveCurrentUser(customer: Customer) {
        customerPreferences.setUserId(customer.id)
        customerPreferences.setUserName(customer.name)
        customerPreferences.setUserEmail(customer.email)
        customerPreferences.setAccessToken(customer.accessToken)
    }

    fun getCurrentUser(): Customer? {
        val userId = customerPreferences.getUserId()
        val userName = customerPreferences.getUserName()
        val userEmail = customerPreferences.getUserEmail()
        val accessToken = customerPreferences.getAccessToken()

        return if (userId !== null && userName != null && userEmail != null && accessToken != null)
            Customer(userId, userName, userEmail, accessToken)
        else
            null
    }
}