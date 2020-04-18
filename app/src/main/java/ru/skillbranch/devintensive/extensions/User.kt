package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.data.User
import ru.skillbranch.devintensive.utils.Utils

fun User.toUserView(){

    val nickName = Utils.transliteration("$firstName $lastName")
    val initials = Utils.toInitials(firstName, lastName)
    val status =
        if (lastName == null) "Еще ни разу не был" else if (isOnline) "online" else "Последний раз был в ${lastVisit?.humanizeDiff()}"



}

