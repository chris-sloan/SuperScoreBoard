package com.chrissloan.superscoreboard.data.datastore.api

@JvmInline
value class UserSettingsStore(private val ds: PreferencesDataStore) : PreferencesDataStore by ds

@JvmInline
value class LastLoginStore(private val ds: PreferencesDataStore) : PreferencesDataStore by ds
