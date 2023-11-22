package com.asusoft.chatingclient.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asusoft.chatingclient.api.member.MemberDto
import com.asusoft.chatingclient.api.member.MemberService
import com.asusoft.chatingclient.util.TAG
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ memberDto ->
                autoLogin.value = !(autoLogin.value as Boolean)
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