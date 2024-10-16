package rs.raf.rma.catalist.login
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.login(
    route: String,
    onSubmit: (String) -> Unit,
    navController: NavController
){ composable(route) {backStackEntry ->
    // Izvuci ViewModel koristeći Hilt ili drugi način
    val viewModel: LoginPageViewModel = hiltViewModel()

    LoginScreen(
        viewModel = viewModel,
        onSubmit = { userData ->
            onSubmit(userData)
            navController.navigate("breeds") // Navigacija na drugi ekran
        }
    )
    }
}

@Composable
fun LoginScreen(
    viewModel: LoginPageViewModel,
    onSubmit: (String) -> Unit
){

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") }
        )
        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") }
        )
        TextField(
            value = nickname,
            onValueChange = { nickname = it },
            label = { Text("Nickname") }
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Button(
            onClick = {
                val userData = "$firstName $lastName $nickname $email"
                viewModel.saveUserData(userData) // Save data to AuthStore
                onSubmit(userData) // Trigger any other submit logic
            }
        ) {
            Text("Submit")
        }
    }

}