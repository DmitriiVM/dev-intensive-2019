package ru.skillbranch.devintensive.models

class UserView(
    val id : String,
    val fullName : String,
    val nickname : String,
    val avatar: String? = null,
    val initials : String? = "offline",
    val status : String?
) {

    fun printMe(){
        println("""
            id : $id 
            fullName : $fullName 
            nickname : $nickname 
            avatar: $avatar
            initials : $initials 
            status : $status 
        """.trimIndent())
    }
}