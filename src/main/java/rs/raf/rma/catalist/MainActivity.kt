package rs.raf.rma.catalist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import rs.raf.rma.catalist.auth.AuthStore
import rs.raf.rma.catalist.navigation.BreedsNavigation
import rs.raf.rma.catalist.ui.theme.CatalistTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var authStore: AuthStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatalistTheme {
            BreedsNavigation(authStore=authStore)
            }
        }
        System.setProperty("java.net.preferIPv4Stack", "true")

    }
}

