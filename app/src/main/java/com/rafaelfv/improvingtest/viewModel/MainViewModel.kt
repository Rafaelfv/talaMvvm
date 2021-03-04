package com.rafaelfv.improvingtest.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.rafaelfv.improvingtest.service.model.Data
import com.rafaelfv.improvingtest.service.model.Locales
import com.rafaelfv.improvingtest.service.repository.ApiData
import com.rafaelfv.improvingtest.utils.STATUS_OK_HTTP
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class MainViewModel : BaseViewModel() {

    @Inject
    lateinit var api: ApiData

    private lateinit var subscription: Disposable
    var loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    var codeHttp: MutableLiveData<Int> = MutableLiveData()
    var data: MutableLiveData<Data> = MutableLiveData()
    var locales: MutableLiveData<Locales> = MutableLiveData()

    init {
        getDataTest()
        getLocales()
    }

    fun getLocales() {
        subscription = api.getLocales()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onSubscribeStart() }
            .doOnTerminate { onTerminate() }
            .subscribe({ onSuccessLocales(it) },
                { error -> onError(error) })
    }

    fun getDataTest() {
        subscription = api.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onSubscribeStart() }
            .doOnTerminate { onTerminate() }
            .subscribe({ onSuccessData(it) },
                { error -> onError(error) })
    }

    private fun onSuccessData(it: Response<Data>) {
        when (it.code()) {
            STATUS_OK_HTTP -> {
                codeHttp.value = 200
                data.value = it.body()
            }
            else -> {
                codeHttp.value = it.code()
            }
        }
    }

    private fun onError(error: Throwable?) {
        error?.printStackTrace()
    }

    private fun onSuccessLocales(it: Response<Locales>) {
        when (it.code()) {
            STATUS_OK_HTTP -> {
                locales.value = it.body()
                codeHttp.value = 200
            }
            else -> {
                codeHttp.value = it.code()
            }
        }
    }

    private fun onTerminate() {
        loadingVisibility.value = View.GONE
    }

    private fun onSubscribeStart() {
        loadingVisibility.value = View.VISIBLE
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}