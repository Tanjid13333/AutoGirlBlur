package com.autogirlblur

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {
    private lateinit var status: TextView
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); render() }
    private fun render() {
        val root = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(32, 40, 32, 24) }
        val title = TextView(this).apply { text = "AutoGirlBlur"; textSize = 28f; setTextColor(Color.rgb(45,35,55)) }
        val note = TextView(this).apply { text = "On-device visual content shield"; textSize = 16f; setPadding(0, 8, 0, 24) }
        status = TextView(this).apply { textSize = 15f; setPadding(0, 12, 0, 12) }
        val enable = Button(this).apply { text = "Enable Accessibility Service"; setOnClickListener { startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)) } }
        val overlay = Button(this).apply { text = "Allow overlay permission"; setOnClickListener { startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))) } }
        val start = Switch(this).apply { text = "Protection ON / OFF"; isChecked = GuardPrefs.enabled(this@MainActivity); setOnCheckedChangeListener { _, on -> GuardPrefs.setEnabled(this@MainActivity, on); ScreenGuardService.refresh(this@MainActivity); updateStatus() } }
        val disclaimer = TextView(this).apply { text = "Tip: Android does not expose every app's pixels reliably. This prototype uses a foreground screen guard and on-device detection; it does not upload screenshots."; setPadding(0, 24, 0, 0) }
        root.addView(title); root.addView(note); root.addView(status); root.addView(enable); root.addView(overlay); root.addView(start); root.addView(disclaimer); setContentView(root); updateStatus()
    }
    private fun updateStatus() { status.text = "Protection: ${if (GuardPrefs.enabled(this)) "ON" else "OFF"}
Accessibility: ${if (BlurAccessibilityService.active) "connected" else "open Settings to connect"}" }
    override fun onResume() { super.onResume(); if (::status.isInitialized) updateStatus() }
}
