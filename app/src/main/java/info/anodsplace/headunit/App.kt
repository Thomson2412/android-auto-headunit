package info.anodsplace.headunit

import android.app.Application
import android.content.Context

class App : Application() {

    private val component: AppComponent by lazy {
        AppComponent(this)
    }

    override fun onCreate() {
        super.onCreate()

        registerReceiver(AapBroadcastReceiver(), AapBroadcastReceiver.filter, RECEIVER_EXPORTED)
    }

    companion object {
        const val DEFAULT_CHANNEL = "default"

        fun get(context: Context): App {
            return context.applicationContext as App
        }
        fun provide(context: Context): AppComponent {
            return get(context).component
        }
    }
}
