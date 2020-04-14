package ru.skillbranch.devintensive.extensions

fun String.truncate(size: Int = 16): String{
    var text = this.trim()
    if (text.length > size) {
        text = "${take(size).trim()}..."
    }
    return text
}

fun String.stripHtml() : String{
    return this.substringAfter("<p>")
        .substringAfter("\">")
        .substringBefore("</p>")
        .replace("\\s+".toRegex(), " ")
}