package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        if (fullName != null && fullName.trim().isEmpty()) {
            return null to null
        }
        val parts: List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var name = ""
        payload.forEach { c ->
            name = "$name${convertLetter(c, divider)}"
        }
        return name
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstLetter = firstName?.firstOrNull()?.toUpperCase() ?: ""
        val secondLetter = lastName?.firstOrNull()?.toUpperCase() ?: ""
        val result = "$firstLetter$secondLetter"
        return if (result.isEmpty()) null else result
    }

    private fun convertLetter(letter: Char, divider: String = " "): String {
        return when (letter.toString()) {
            " " -> divider
            "а" -> "a"
            "б" -> "b"
            "в" -> "v"
            "г" -> "g"
            "д" -> "d"
            "е" -> "e"
            "ё" -> "e"
            "ж" -> "zh"
            "з" -> "z"
            "и" -> "i"
            "й" -> "i"
            "к" -> "k"
            "л" -> "l"
            "м" -> "m"
            "н" -> "n"
            "о" -> "o"
            "п" -> "p"
            "р" -> "r"
            "с" -> "s"
            "т" -> "t"
            "у" -> "u"
            "ф" -> "f"
            "х" -> "h"
            "ц" -> "c"
            "ч" -> "ch"
            "ш" -> "sh"
            "щ" -> "sh"
            "ъ" -> ""
            "ы" -> "i"
            "ь" -> ""
            "э" -> "e"
            "ю" -> "yu"
            "я" -> "ya"
            "А" -> "a".toUpperCase()
            "Б" -> "b".toUpperCase()
            "В" -> "v".toUpperCase()
            "Г" -> "g".toUpperCase()
            "Д" -> "d".toUpperCase()
            "Е" -> "e".toUpperCase()
            "Ё" -> "e".toUpperCase()
            "Ж" -> "zh".toUpperCase()
            "З" -> "z".toUpperCase()
            "И" -> "i".toUpperCase()
            "Й" -> "i".toUpperCase()
            "К" -> "k".toUpperCase()
            "Л" -> "l".toUpperCase()
            "М" -> "m".toUpperCase()
            "Н" -> "n".toUpperCase()
            "О" -> "o".toUpperCase()
            "П" -> "p".toUpperCase()
            "Р" -> "r".toUpperCase()
            "С" -> "s".toUpperCase()
            "Т" -> "t".toUpperCase()
            "У" -> "u".toUpperCase()
            "Ф" -> "f".toUpperCase()
            "Х" -> "h".toUpperCase()
            "Ц" -> "c".toUpperCase()
            "Ч" -> "ch".toUpperCase()
            "Ш" -> "sh".toUpperCase()
            "Щ" -> "sh".toUpperCase()
            "Ъ" -> ""
            "Ы" -> "i".toUpperCase()
            "Ь" -> ""
            "Э" -> "e".toUpperCase()
            "Ю" -> "yu".toUpperCase()
            "Я" -> "ya".toUpperCase()
            else -> letter.toString()
        }
    }
}