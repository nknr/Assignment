package com.neeraj.assignment.utils

import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import android.view.accessibility.AccessibilityManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.neeraj.assignment.R

object AppUtils {

    private const val TAG = "AppUtils"

    fun isEnabled(context: Context): Boolean {
        val accessibilityManager = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        val enabledServices = accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC)
        for (enabledService in enabledServices) {
            val enabledServiceInfo = enabledService.resolveInfo.serviceInfo
            if (enabledServiceInfo.packageName == context.packageName && enabledServiceInfo.name == MyAccessibilityService::class.java.name) return true
        }
        return false
    }

     fun openSettings(context: Context) {
        Log.i(TAG, "Open accessibility settings")
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        context.startActivity(intent)
    }

    fun replaceFragment(activity: FragmentActivity?, fragment: Fragment, from: String) {
        activity?.let {
            it.supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment).addToBackStack(from)
                .commitAllowingStateLoss()
        }
    }
}