package info.anodsplace.headunit.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import info.anodsplace.headunit.App

import info.anodsplace.headunit.utils.AppLog

class RemoteControlReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_MEDIA_BUTTON == intent.action) {
            val event: KeyEvent? = intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT, KeyEvent::class.java)
            if (event != null) {
                AppLog.i { "ACTION_MEDIA_BUTTON: " + event.keyCode }
                App.provide(context).transport.send(event.keyCode, event.action == KeyEvent.ACTION_DOWN)
            }
        }
    }
}
