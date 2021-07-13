package com.example.meditopitestcase.util

import android.content.Context

class SharedPrefUtil {

    companion object {

        private val prefName = "MyPref"

        private val USERNAME = "USER_NAME"
/*
        private val USER_SAVE = "USER_SAVE"
*/


        fun setUserName (context: Context, username:String) {

            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

            pref.edit().putString(USERNAME, username).apply()
        }

        fun getUserName (context:Context) : String {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(USERNAME, "")!!

        }
/*

        fun setSaveUser(context: Context, isSave:Boolean) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putBoolean(USER_SAVE, isSave).apply()
        }

        fun getSaveUser(context: Context) : Boolean {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getBoolean(USER_SAVE, false)
        }
*/



    }

}