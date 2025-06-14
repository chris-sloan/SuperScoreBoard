package com.chrissloan.superscoreboard.data.datastore.api

interface PreferencesDataStore {
    suspend fun saveString(key: String, value: String)
    suspend fun getString(key: String): String?

    suspend fun saveInt(key: String, value: Int)
    suspend fun getInt(key: String): Int?

    suspend fun saveBoolean(key: String, value: Boolean)
    suspend fun getBoolean(key: String): Boolean?

    suspend fun saveFloat(key: String, value: Float)
    suspend fun getFloat(key: String): Float?

    suspend fun saveLong(key: String, value: Long)
    suspend fun getLong(key: String): Long?

    suspend fun saveStringSet(key: String, value: Set<String>)
    suspend fun getStringSet(key: String): Set<String>?
}
