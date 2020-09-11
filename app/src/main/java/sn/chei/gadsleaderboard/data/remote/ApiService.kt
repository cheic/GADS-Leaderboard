package sn.chei.gadsleaderboard.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sn.chei.gadsleaderboard.BuildConfig


object OkHttpProvider {

    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofitForDatas = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val retrofitForProjectSubmission = Retrofit.Builder()
        .baseUrl(BuildConfig.SUBMISSION_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofitForDatas.create(service)
    }

    fun <T> buildServiceForProjectSubmission(service: Class<T>): T {
        return retrofitForProjectSubmission.create(service)
    }
}
