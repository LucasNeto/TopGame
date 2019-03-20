package com.lucassantana.topgame.prefs

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by capgemini on 19/03/2019.
 */

class Prefs {
    companion object {
        private var mInstance: Prefs? = null

        val instance: Prefs
            get() {
                if (mInstance == null) {
                    mInstance = Prefs()
                }
                return mInstance as Prefs
            }
    }


    private val FILE_NAME = "com.lucassantana.topgame.prefs"
    private var shared : SharedPreferences? = null


    fun init(context: Context){
        shared = context.getSharedPreferences(FILE_NAME,0)
    }

    fun set( key: String, value: String){
        if (shared == null) return
        val edt = shared!!.edit()
        edt.putString(key,value)
        edt.apply()
    }

    fun get(key: String) : String? {
        if (shared == null) return null
        return shared!!.getString(key,null)
    }


}