package sn.chei.gadsleaderboard.data.remote

import androidx.core.os.BuildCompat
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sn.chei.gadsleaderboard.BuildConfig


object OkHttpProvider{

private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> buildService(service: Class<T>):  T {
        return retrofit.create(service)
    }
}