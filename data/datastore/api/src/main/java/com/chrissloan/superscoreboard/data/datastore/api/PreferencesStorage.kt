package com.chrissloan.superscoreboard.data.datastore.api

sealed class PreferencesStorage(val qualifier: String) {
    data object LastLogin : PreferencesStorage("LastLogin")
    data object UserSettings : PreferencesStorage("UserSettings")
}
