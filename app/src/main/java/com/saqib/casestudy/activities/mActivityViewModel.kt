package com.saqib.casestudy.activities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saqib.casestudy.model.PixaBayResponse
import com.saqib.casestudy.network.NetworkCall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class mActivityViewModel : ViewModel() {

    var showProgress: MutableLiveData<Boolean> = MutableLiveData()
    var showErrorMessage: MutableLiveData<String> = MutableLiveData()
    var pixaBayResponseValue : MutableLiveData<PixaBayResponse> = MutableLiveData()

    fun callApi() {
        showProgress.postValue(true)
        NetworkCall.instance?.editorsChoice()?.enqueue(object : Callback<PixaBayResponse?> {
            override fun onResponse(call: Call<PixaBayResponse?>, response: Response<PixaBayResponse?>) {
                pixaBayResponseValue.postValue(response.body()!!)
                showProgress.postValue(false)
            }

            override fun onFailure(call: Call<PixaBayResponse?>, t: Throwable) {
                showProgress.postValue(false)
                showErrorMessage.postValue("Something went wrong please try latter.")
            }
        })
    }
}
