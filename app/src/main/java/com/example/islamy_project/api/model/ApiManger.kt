package com.example.islamy_project.api.model


import android.util.Log
import com.example.islamy_project.constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class ApiManger {
    companion object {

        private var retrofit: Retrofit? = null

        fun getInstance(): Retrofit {

            val httpLoggingInterceptor =
                HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                    override fun log(message: String) {
                        Log.e("api", message)
                    }

                })
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()


            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }

        fun getWebService(): WebService {
            return getInstance().create(WebService::class.java)
        }
    }
}