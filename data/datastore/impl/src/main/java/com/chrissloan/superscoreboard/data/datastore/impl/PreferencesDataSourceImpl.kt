package com.chrissloan.superscoreboard.data.datastore.impl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import com.chrissloan.superscoreboard.data.datastore.api.PreferencesDataStore
import com.chrissloan.superscoreboard.data.datastore.api.PreferencesStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first

class PreferencesDataSourceImpl(
    context: Context,
    preferencesStorage: PreferencesStorage
) : PreferencesDataStore {

    private val dataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create(
        scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
        produceFile = { context.preferencesDataStoreFile(preferencesStorage.qualifier) }
    )

    // String
    override suspend fun saveString(key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        dataStore.edit { it[prefKey] = value }
    }

    override suspend fun getString(key: String): String? {
        val prefKey = stringPreferencesKey(key)
        return dataStore.data.first()[prefKey]
    }

    // Int
    override suspend fun saveInt(key: String, value: Int) {
        val prefKey = intPreferencesKey(key)
        dataStore.edit { it[prefKey] = value }
    }

    override suspend fun getInt(key: String): Int? {
        val prefKey = intPreferencesKey(key)
        return dataStore.data.first()[prefKey]
    }

    // Boolean
    override suspend fun saveBoolean(key: String, value: Boolean) {
        val prefKey = booleanPreferencesKey(key)
        dataStore.edit { it[prefKey] = value }
    }

    override suspend fun getBoolean(key: String): Boolean? {
        val prefKey = booleanPreferencesKey(key)
        return dataStore.data.first()[prefKey]
    }

    // Float
    override suspend fun saveFloat(key: String, value: Float) {
        val prefKey = floatPreferencesKey(key)
        dataStore.edit { it[prefKey] = value }
    }

    override suspend fun getFloat(key: String): Float? {
        val prefKey = floatPreferencesKey(key)
        return dataStore.data.first()[prefKey]
    }

    // Long
    override suspend fun saveLong(key: String, value: Long) {
        val prefKey = longPreferencesKey(key)
        dataStore.edit { it[prefKey] = value }
    }

    override suspend fun getLong(key: String): Long? {
        val prefKey = longPreferencesKey(key)
        return dataStore.data.first()[prefKey]
    }

    // String Set
    override suspend fun saveStringSet(key: String, value: Set<String>) {
        val prefKey = stringSetPreferencesKey(key)
        dataStore.edit { it[prefKey] = value }
    }

    override suspend fun getStringSet(key: String): Set<String>? {
        val prefKey = stringSetPreferencesKey(key)
        return dataStore.data.first()[prefKey]
    }
}
