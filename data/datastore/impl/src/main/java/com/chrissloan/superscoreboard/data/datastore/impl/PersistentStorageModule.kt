package com.chrissloan.superscoreboard.data.datastore.impl

import com.chrissloan.superscoreboard.data.datastore.api.LastLoginStore
import com.chrissloan.superscoreboard.data.datastore.api.PreferencesDataStore
import com.chrissloan.superscoreboard.data.datastore.api.PreferencesStorage
import com.chrissloan.superscoreboard.data.datastore.api.UserSettingsStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val persistentStorageModule = module {

    factory<PreferencesDataStore> { (type: PreferencesStorage) ->
        PreferencesDataSourceImpl(androidContext(), type)
    }

    factory<UserSettingsStore> {
        UserSettingsStore(get { parametersOf(PreferencesStorage.UserSettings) })
    }
    factory<LastLoginStore> {
        LastLoginStore(get { parametersOf(PreferencesStorage.LastLogin) })
    }
}
