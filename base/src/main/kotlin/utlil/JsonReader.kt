package utlil

import android.content.Context
import data.remote.Response
import kotlinx.serialization.json.Json

class JsonReader(private val context: Context) {

    fun read(jsonName: String): Response {
        val text = context.assets.open(jsonName).bufferedReader().use { it.readText() }
        val response = Json.decodeFromString<Response>(text)
        return response
    }
}
