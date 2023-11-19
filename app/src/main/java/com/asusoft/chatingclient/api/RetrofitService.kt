package com.asusoft.chatingclient.api

interface RetrofitService {
    fun refreshService()

    fun getType(): RetrofitClient.RepositoryType
}