package com.chrissloan.superscoreboard.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json

open class BaseApi {
    val client: HttpClient by lazy { HttpClient(Android) }
    val baseUrl = "https://pyates-twocircles.github.io/two-circles-tech-test/"

    suspend inline fun <reified T> fetch(path: String): T? {
        var retryCount = 0
        while (retryCount < MAX_RETRIES) {
            try {
                return Json.decodeFromString<T>(client.get(baseUrl + path).body())
            } catch (e: Exception) {
                if (isRetryableError(e)) {
                    retryCount++
                    delay(DELAY_MILLIS)
                } else {
                    return null
                }
            }
        }
        return null
    }

    fun isRetryableError(e: Exception): Boolean {
        println("<<<<< - isRetryableError : $e")
        return e is ClientRequestException || e is ServerResponseException
    }

    companion object {
        const val MAX_RETRIES = 3
        const val DELAY_MILLIS = 500L
    }
}
