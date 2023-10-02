package com.cloudsect.myapplication.util

import java.util.regex.Pattern

class EmailValidator {
    fun isEmailValid(email: String): Boolean {
        val regexPattern = Pattern.compile(
            "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        )
        val matcher = regexPattern.matcher(email)
        return matcher.matches()
    }

}