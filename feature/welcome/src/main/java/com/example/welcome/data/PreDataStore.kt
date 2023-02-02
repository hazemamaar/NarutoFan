package com.example.welcome.data

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.emptyPreferences
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException



const val PREFERENCE_NAME = "my_preference"

class PreDataStore(context: Context) {
    private object PreferenceKeys {
        val name = preferencesKey<String>("my_name")
    }

    val dataStore: DataStore<androidx.datastore.preferences.Preferences> = context.createDataStore(name = "gfg-datastore")

    suspend fun saveToDataStore(name: String) {
        dataStore.edit { preference ->
            preference[PreferenceKeys.name] = name
        }
    }

    val readFromDataStore: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preference ->
            val myName = preference[PreferenceKeys.name] ?: "none"
            myName
        }

}