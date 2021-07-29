package com.avister.utilities


import android.content.Context
import android.content.res.Resources
import android.os.Environment
import androidx.core.net.toUri
import com.avister.R
import java.io.InputStream
import java.util.*


class ConfigurationManager(context: Context) {
    private val properties = Properties()

    init {
        val resources: Resources = context.resources
        val rawResource: InputStream = resources.openRawResource(R.raw.config)


        properties.load(rawResource)
    }

    private fun propWithSpecialCases(configElement: String, prop: String): List<String> {
        return when (configElement) {
//            ("mainMusicDir" to "Environment.DIRECTORY_MUSIC") -> Environment.DIRECTORY_MUSIC.toUri().toString()
            "generationModelsNames" -> prop.split(",").map { it.removeSurrounding(" ") }
            else -> listOf(prop)
        }
    }

    operator fun get(configElement: String): List<String> {
        when (val prop = properties[configElement]) {
            is String -> return propWithSpecialCases(configElement, prop)
            else -> throw IllegalArgumentException("Config Element $configElement is not reachable")
        }

    }
}