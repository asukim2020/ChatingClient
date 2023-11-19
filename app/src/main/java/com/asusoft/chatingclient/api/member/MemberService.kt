package com.asusoft.chatingclient.api.member

import com.asusoft.chatingclient.api.RetrofitClient
import com.asusoft.chatingclient.api.RetrofitService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import io.reactivex.Flowable

object MemberService: RetrofitService {

    private var memberRepository: MemberRepository = getType().getRepository() as MemberRepository
    private val objectMapper = ObjectMapper()

    override fun refreshService() {
        memberRepository = getType().getRepository() as MemberRepository
    }

    override fun getType(): RetrofitClient.RepositoryType {
        return RetrofitClient.RepositoryType.MEMBER
    }

    fun login(dto: MemberDto): Flowable<MemberDto> {
        val map = objectMapper.convertValue<Map<String, String>>(dto)
        return memberRepository.login(map)
    }
}