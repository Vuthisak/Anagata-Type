package com.anagata.typingkit.util

import android.content.Context
import com.anagata.typingkit.repository.model.MockFontWrapper
import com.google.gson.Gson
import java.io.IOException
import java.nio.charset.Charset

val gson = Gson()

fun getMockFonts(context: Context): MockFontWrapper? {
    return try {
        val inputStream = context.assets.open("MockData.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val jsonString = String(buffer, Charset.forName("UTF-8"))
        gson.fromJson(jsonString, MockFontWrapper::class.java)
    } catch (ex: IOException) {
        ex.printStackTrace()
        null
    }
}
