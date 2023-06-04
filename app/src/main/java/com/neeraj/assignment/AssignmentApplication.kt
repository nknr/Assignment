package com.neeraj.assignment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AssignmentApplication : Application() {

    companion object {
        var isServiceObserve = false
    }
}