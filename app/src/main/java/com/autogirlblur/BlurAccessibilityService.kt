package com.autogirlblur
import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
class BlurAccessibilityService : AccessibilityService() {
    companion object { @Volatile var active=false }
    override fun onServiceConnected(){ super.onServiceConnected(); active=true }
    override fun onAccessibilityEvent(event: AccessibilityEvent?) { if (GuardPrefs.enabled(this)) ScreenGuardService.refresh(this) }
    override fun onInterrupt(){ active=false }
    override fun onDestroy(){ active=false; super.onDestroy() }
}
