package info.anodsplace.headunit.main

import android.Manifest
import android.os.Bundle
import android.view.KeyEvent
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import info.anodsplace.headunit.R
import info.anodsplace.headunit.utils.AppLog
import info.anodsplace.headunit.utils.hideSystemUI

class MainActivity : FragmentActivity() {
    var keyListener: KeyListener? = null

    interface KeyListener {
        fun onKeyEvent(event: KeyEvent): Boolean
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_content, SettingsFragment())
                .commit()

        ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_FINE_LOCATION
        ), PERMISSION_REQUEST_CODE)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        window.decorView.hideSystemUI()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        AppLog.i { "onKeyDown: $keyCode "}

        return keyListener?.onKeyEvent(event) ?: super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        AppLog.i { "onKeyUp: $keyCode" }

        return keyListener?.onKeyEvent(event) ?: super.onKeyUp(keyCode, event)
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 97
    }
}
