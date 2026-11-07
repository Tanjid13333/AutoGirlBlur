package com.autogirlblur

import android.app.*
import android.content.*
import android.graphics.Color
import android.os.*
import android.view.*

class ScreenGuardService : Service() {
    private var wm: WindowManager? = null
    private var shield: View? = null
    override fun onBind(intent: Intent?) = null
    override fun onStartCommand(intent: Intent?, flags: Int, id: Int): Int { if (Build.VERSION.SDK_INT>=26) startForeground(11, notification()); if (GuardPrefs.enabled(this)) showShield() else hideShield(); return START_STICKY }
    private fun showShield(){ if (shield!=null || !Settings.canDrawOverlays(this)) return; wm=getSystemService(WINDOW_SERVICE) as WindowManager; shield=View(this).apply{ setBackgroundColor(Color.BLACK); alpha=.98f }; val type=if(Build.VERSION.SDK_INT>=26) WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY else WindowManager.LayoutParams.TYPE_PHONE; val p=WindowManager.LayoutParams(-1,-1,type, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, -3); wm?.addView(shield,p) }
    private fun hideShield(){ shield?.let{ wm?.removeView(it) }; shield=null }
    private fun notification(): Notification { val ch="guard"; if(Build.VERSION.SDK_INT>=26) (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(NotificationChannel(ch,"Protection",NotificationManager.IMPORTANCE_LOW)); return Notification.Builder(this,ch).setContentTitle("AutoGirlBlur active").setContentText("Protection is running on-device").setSmallIcon(android.R.drawable.ic_lock_lock).build() }
    override fun onDestroy(){ hideShield(); super.onDestroy() }
    companion object { fun refresh(c: Context){ val i=Intent(c,ScreenGuardService::class.java); if(Build.VERSION.SDK_INT>=26)c.startForegroundService(i) else c.startService(i) } }
}
