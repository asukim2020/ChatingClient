package com.asusoft.chatingclient.api

import com.asusoft.chatingclient.api.member.MemberRepository
import com.asusoft.chatingclient.api.member.MemberService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private var LOCAL_URL = "http://172.30.1.10:8080/"

    private var url = LOCAL_URL

    enum class RepositoryType(private val value: String) {
        MEMBER("MEMBER"),
        NONE("NONE");
        fun fromString(value: String): RepositoryType {
            return when(value) {
                MEMBER.value -> MEMBER
                else -> NONE
            }
        }

        fun getInstance(): RetrofitService? {
            return when(this) {
                MEMBER -> MemberService
                NONE -> null
            }
        }

        fun getRepository(): Any? {
            val retrofit = build(url)

            return when(this) {
                MEMBER -> retrofit.create(MemberRepository::class.java)
                NONE -> null
            }
        }
    }

    private fun createClientAuth(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        okHttpClientBuilder

            // set timeout 10s
            .connectTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)

            .addNetworkInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    )
            )

        return okHttpClientBuilder.build()
    }

    private var _build: Retrofit? = null
    private fun build(url: String = this.url): Retrofit {
        setUrl(url)

        return when (_build) {
            null -> {
                createBuild(url)
            }

            else -> {
                _build!!
            }
        }
    }

    private fun createBuild(url: String = this.url): Retrofit {
        setUrl(url)

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(createClientAuth())
            .build()
    }

    fun reBuild(url: String) {
        setUrl(url)

        _build = createBuild(url)

        for (type in RepositoryType.values()) {
            type.getInstance()?.refreshService()
        }
    }

    private fun setUrl(url: String) {
        if (url != this.url) {
            this.url = url
        }
    }

}