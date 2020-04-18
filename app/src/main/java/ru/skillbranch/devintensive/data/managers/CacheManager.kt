package ru.skillbranch.devintensive.data.managers

import android.util.Log
import androidx.lifecycle.MutableLiveData
import ru.skillbranch.devintensive.extensions.mutableLiveData
import ru.skillbranch.devintensive.models.data.Chat
import ru.skillbranch.devintensive.models.data.User
import ru.skillbranch.devintensive.utils.DataGenerator

//имитация базы данных
object CacheManager {
    private val chats = mutableLiveData(DataGenerator.stabChats)
    private val users = mutableLiveData(DataGenerator.stabUsers)

    fun loadChats(): MutableLiveData<List<Chat>>{
        val tt = DataGenerator.stabChats
        tt.forEach {
            val mm = it.messages
            mm.forEach { m ->
                Log.d("mmm", "CacheManager :  loadChats --  ${m.date}")
            }

        }
        Log.d("mmm", "CacheManager :  loadChats --  ")
        return chats
    }

    fun findUsersByIds(ids: List<String>): List<User> {
        return users.value!!.filter { ids.contains(it.id) }
    }

    fun nextChatId(): String {
        return "${chats.value!!.size}"
    }

    fun insertChat(chat: Chat){
        val copy = chats.value!!.toMutableList()
        copy.add(chat)
        chats.value = copy
    }
}