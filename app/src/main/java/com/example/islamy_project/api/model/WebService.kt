package com.example.islamy_project.api.model

import retrofit2.Call
import retrofit2.http.GET

interface WebService {
    @GET("radio/radio_arabic.json")
    fun getRadio(): Call<RadioResponse>
}