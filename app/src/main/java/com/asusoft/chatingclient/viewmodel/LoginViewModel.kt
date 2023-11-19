package com.asusoft.chatingclient.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asusoft.chatingclient.api.member.MemberDto
import com.asusoft.chatingclient.api.member.MemberService
import com.orhanobut.logger.Logger
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
        // TODO: - click event 가 안들어 온다. 해결할 것
        Logger.d("click login")
        val memberDto = MemberDto(null, id.value, pw.value)
        MemberService.login(memberDto)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ memberDto ->
                Logger.d(memberDto)
                autoLogin.value = !(autoLogin.value as Boolean)
            }, { thowable ->
                Logger.d(thowable.localizedMessage)
            }, { // complete

            }, {  subscription ->
                subscription.request(Long.MAX_VALUE)
            })
    }

    fun signUp() {
        // TODO: - 개발
    }
}