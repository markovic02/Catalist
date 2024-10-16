package rs.raf.rma.catalist

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import rs.raf.rma.catalist.auth.AuthStore
import javax.inject.Inject

@HiltAndroidApp
class App: Application() {

    @Inject lateinit var  authStore: AuthStore

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        val authData = authStore.authData.value

        Log.d("DATASTORE", "AuthData = $authData")
    }
}