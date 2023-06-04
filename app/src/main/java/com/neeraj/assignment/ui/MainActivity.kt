package com.neeraj.assignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.neeraj.assignment.R
import com.neeraj.assignment.databinding.ActivityMainBinding
import com.neeraj.assignment.utils.AppUtils
import com.neeraj.assignment.utils.constant.FragmentTag
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isAccessibilityPermissionGranted: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActionBar(getString(R.string.post))
        initResource()
    }

    private fun initResource() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppUtils.replaceFragment(this, PostFragment.newInstance(), FragmentTag.POST)
    }

    fun initActionBar(title: String, isHomeButtonEnable: Boolean = false) {
        supportActionBar?.apply {
            this.title = title
            setHomeButtonEnabled(isHomeButtonEnable)
            setDisplayHomeAsUpEnabled(isHomeButtonEnable)
            setHomeAsUpIndicator(ActivityCompat.getDrawable(this@MainActivity, R.drawable.ic_navigation_up))
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            isAccessibilityPermissionGranted = AppUtils.isEnabled(this@MainActivity)
            invalidateOptionsMenu()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val iconResId = if (isAccessibilityPermissionGranted) R.drawable.ic_on_notifications else R.drawable.ic_off_notifications
        menu?.findItem(R.id.action_accessibility)?.icon = ActivityCompat.getDrawable(this, iconResId)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_accessibility -> {
                AppUtils.openSettings(this)
                true
            }

            android.R.id.home -> {
                onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentByTag(FragmentTag.POST_DETAIL)
        if (fragment is PostDetailFragment) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            super.onBackPressed()
        }
    }
}