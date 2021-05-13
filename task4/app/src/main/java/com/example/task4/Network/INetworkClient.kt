package com.example.task4.Network

import com.example.task4.Room.HabitEntity
import retrofit2.http.*

interface INetworkClient {
    @GET("habit")
    suspend fun getAll(@Header("Authorization") authorization: String): List<HabitEntity>

    @PUT("habit")
    suspend fun addOrUpdate(@Header("Authorization") authorization: String, @Body habit: HabitEntity)

    @HTTP(method="DELETE", path="habit", hasBody=true)
    suspend fun delete(@Header("Authorization") authorization: String, @Body uid: UID)

    @POST("habit_done")
    suspend fun habitDone(@Header("Authorization") authorization: String)
}