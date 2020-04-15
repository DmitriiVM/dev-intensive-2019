package ru.skillbranch.devintensive.extensions

import java.util.regex.Pattern

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

const val NAME_PATTERN = """[A-ZА-Я].*"""
const val PROFESSION_PATTERN = """[a-zа-я].*"""
const val MATERIAL_PATTERN = """\D*"""
const val BDAY_PATTERN = """\d*"""
const val SERIAL_PATTERN = """\d{7}"""

fun validateText(pattern: String, text : String) = Pattern.compile(pattern).matcher(text).matches()