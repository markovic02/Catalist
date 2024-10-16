package rs.raf.rma.catalist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import rs.raf.rma.catalist.auth.AuthStore
import rs.raf.rma.catalist.breeds.list.breeds
import rs.raf.rma.catalist.detailed.breedsDetails
import rs.raf.rma.catalist.login.login

@Composable
fun BreedsNavigation(
    authStore : AuthStore
) {
    val navController = rememberNavController()
    val authData by authStore.authData.collectAsState()

    val isFirstLaunch = remember(authData){authData.isEmpty()}
    val startDest : String = when(isFirstLaunch){
        true -> "login"
        false -> "breeds"
    }

    NavHost(
        navController = navController,
        startDestination = startDest,
    ){
        breeds(
            route = "breeds",
            onBreedsClick =  {breedId ->navController.navigate(route = "breeds/$breedId")},
            navController = navController
        )

        breedsDetails(
            route = "breeds/{breedId}",
            arguments = listOf(
                navArgument(name = "breedId"){
                    nullable = false
                    type = NavType.StringType
                }
            ),
            onClose = {navController.navigateUp()},
            navController = navController,

        )
        login(
            route = "login",
            onSubmit = {navController.navigate(route = "breeds")},
            navController = navController
        )

    }

}
inline val SavedStateHandle.breedId: String
    get() = checkNotNull(get("breedId")) { "breedId is mandatory" }