package com.neeraj.assignment

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED

class MyAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        event?.let {
            Log.i(TAG, "onAccessibilityEvent() event: ${it.eventType}, ${it.className}, ${it.source?.className} packageName ${it.packageName}, ${it.eventType == TYPE_WINDOW_CONTENT_CHANGED}")
        }
    }

    override fun onInterrupt() {
        TODO("Not yet implemented")
    }

    companion object {
        private const val TAG = "MyAccessibilityService"
    }
}