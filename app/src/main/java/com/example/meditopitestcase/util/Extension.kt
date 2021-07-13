package com.example.meditopitestcase.util

import com.example.meditopitestcase.api.model.Message
import com.example.meditopitestcase.api.model.User
import java.util.*


fun sendMessage(username: String, message: String): Message {
    return Message(
        id = generateId(),
        text = message,
        timestamp = System.currentTimeMillis()/1000,
        User(
            id = "3",
            avatarURL = "",
            nickname = username
        )
    )
}

fun generateId():String{
    val chars = "abcdefghijklmnopqrstuvwxyz_1234567890".toCharArray()
    val sb = StringBuilder(7)
    val random = Random()
    for (i in 0..7) {
        val c = chars[random.nextInt(chars.size)]
        sb.append(c)
    }
    val output = sb.toString()
    return output
}