package com.asusoft.chatingclient.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asusoft.chatingclient.api.member.MemberDto
import com.asusoft.chatingclient.api.member.MemberService
import com.asusoft.chatingclient.util.TAG
import com.orhanobut.logger.Logger
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(

): ViewModel() {

    // TODO: - 테스트 용 데이터, 테스트 완료 후 제거
    val idString = "asukim2020"
    val pwString = "1234"

    val id = MutableLiveData<String>(idString)
    val pw = MutableLiveData<String>(pwString)
    val autoLogin = MutableLiveData<Boolean>(false)

    fun login() {
        Logger.t(TAG.LOGIN).d("click login id: ${id.value}, pw: ${pw.value}")
        val memberDto = MemberDto(-1, "", id.value, pw.value)
        MemberService.login(memberDto)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ memberDto ->
                Logger.t(TAG.LOGIN).d(memberDto)
                CoroutineScope(Dispatchers.Main).launch {
                    autoLogin.value = !(autoLogin.value as Boolean)
                }
                Logger.t(TAG.LOGIN).d("success login -> $memberDto")
            }, { thowable ->
                Logger.t(TAG.LOGIN).d("error -> ${thowable.localizedMessage}")
            }, { // complete

            }, {  subscription ->
                subscription.request(Long.MAX_VALUE)
            })
    }

    fun signUp() {
        // TODO: - 개발
    }
}