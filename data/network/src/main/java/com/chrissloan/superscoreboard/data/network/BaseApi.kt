package com.chrissloan.superscoreboard.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get

open class BaseApi {
    private val client: HttpClient by lazy { HttpClient(Android) }
    internal val baseUrl = "https://pyates-twocircles.github.io/two-circles-tech-test/"

    suspend fun fetch(path: String) = client.get(baseUrl + path)
}