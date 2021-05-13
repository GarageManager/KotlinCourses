package com.example.task4.Network

import com.google.gson.*
import java.lang.reflect.Type

class UID(val uid: String)

class UIDJsonSerializer: JsonSerializer<UID> {
    override fun serialize(src: UID?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement =
        JsonObject().apply {
            addProperty("uid", src?.uid)
        }
}