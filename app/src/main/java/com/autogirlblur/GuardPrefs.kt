package com.autogirlblur
import android.content.Context
object GuardPrefs { private const val P="guard"; private const val E="enabled"; fun enabled(c: Context)=c.getSharedPreferences(P,0).getBoolean(E,false); fun setEnabled(c: Context,v:Boolean)=c.getSharedPreferences(P,0).edit().putBoolean(E,v).apply() }
