package com.example.task4

import com.example.task4.Filter.HabitFilter
import com.example.task4.Habit.HabitInfo
import com.example.task4.Network.*
import com.example.task4.Room.HabitEntity
import com.example.task4.Room.HabitsDao
import com.example.task4.Room.RoomMapper
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HabitsModel {
    private const val token = "00eba224-21b4-4533-87ca-c1fc9c37061a"
    private const val url = "https://droid-test-server.doubletapp.ru/api/"
    private lateinit var habitsDao: HabitsDao
    lateinit var habits: Flow<List<HabitInfo>>
    private val gson = GsonBuilder()
        .registerTypeAdapter(HabitEntity::class.java, HabitEntityJsonSerializer())
        .registerTypeAdapter(HabitEntity::class.java, HabitEntityJsonDeserializer())
        .registerTypeAdapter(UIDJsonSerializer::class.java, UIDJsonSerializer())
        .create()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    private val service: INetworkClient = retrofit.create(INetworkClient::class.java)

    fun init(habitsDao: HabitsDao): HabitsModel
    {
        this.habitsDao = habitsDao
        GlobalScope.launch(Dispatchers.IO) { habitsDao.deleteAll() }
        GlobalScope.launch(Dispatchers.IO) {
            service.getAll(token).forEach{ habitsDao.insert(it) }
        }

        habits = habitsDao.getAll().map { list -> list.map { RoomMapper.habitEntityToHabitInfo(it) } }
        return this
    }

    suspend fun insert(habit: HabitInfo) {
        service.addOrUpdate(token, RoomMapper.habitInfoToHabitEntity(habit))
        service.getAll(token).forEach{ habitsDao.insert(it) }
    }

    suspend fun delete(habit: HabitInfo, uid: String)
    {
        habitsDao.delete(RoomMapper.habitInfoToHabitEntity(habit))
        service.delete(token, UID(uid))
    }


    fun loadMain(function: (HabitFilter) -> Unit){
        function.invoke(HabitFilter())
    }

    fun loadEditor(function: (HabitInfo) -> Unit)
    {
        function.invoke(HabitInfo.defaultInstance())
    }
}