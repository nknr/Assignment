package com.neeraj.assignment.utils

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast
import com.neeraj.assignment.R

class MyAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        event?.let {
            Log.i(TAG, "onAccessibilityEvent() eventType: ${it.eventType}, package: ${it.packageName}, ${it.className}")
            if (it.className?.startsWith(applicationContext.getString(R.string.observe_app_package_name)) == true) {
                Toast.makeText(applicationContext, getString(R.string.observe_app_description), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onInterrupt() {
        Log.i(TAG, "onInterrupt()")
    }

    companion object {
        private const val TAG = "MyAccessibilityService"
    }
}