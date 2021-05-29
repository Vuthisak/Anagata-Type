package com.anagata.typingkit.repository.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

@JvmInline
value class MockFontWrapper(
    val mockFonts: ArrayList<MockFont>
) : Serializable
