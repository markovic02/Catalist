package rs.raf.rma.catalist.detailed

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.compose.SubcomposeAsyncImage

fun NavGraphBuilder.breedsDetails(
    route: String,
    arguments: List<NamedNavArgument>,
    navController: NavController,
    onClose: ()->Unit
) = composable(
    route = route,
    arguments = arguments
){
    navBackStackEntry ->
    val breedId = navBackStackEntry.arguments?.getString("breedId")
        ?: throw IllegalStateException("BreedId required")

    val detailedViewModel: DetailedViewModel = hiltViewModel(navBackStackEntry)
//    val detailedViewModel = viewModel<DetailedViewModel>(
////        factory = object : ViewModelProvider.Factory{
////            @Suppress("UNCHECKED_CAST")
////            override fun <T : ViewModel> create(modelClass: Class<T>): T {
////                return DetailedViewModel(id = breedId) as T
////            }
////        }
//    )

    val state = detailedViewModel.state.collectAsState()
    DetailedScreen(
        state = state.value,
        onClose = {navController.navigate(route = "breeds")},

    )

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedScreen(
    state: DetailedState,
    onClose: () -> Unit
) {
//    @Composable
//    fun ImageFromUrl(url: String) {
//        AsyncImage(
//            model = ImageRequest.Builder(LocalContext.current)
//                .data(url)
//                .crossfade(true)
//                .build(),
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp) // Adjust height as needed
//        )
//    }
//    @Composable
//    fun PreviewImageFromUrl() {
//        ImageFromUrl(state.data.image!!)
//    }
   Scaffold(
       topBar = {   MediumTopAppBar(
           title = { Text(text = "Details") },
           navigationIcon = {
               AppIconButton(
                   imageVector = Icons.Default.ArrowBack,
                   onClick = onClose,
               )
           }
       )
       },
       content = {paddingValues ->
           if (state.fetching){
               Box(modifier = Modifier.fillMaxSize(),
                   contentAlignment = Alignment.Center,
                   ){
                   CircularProgressIndicator()
               }
           }else{
               LazyColumn(
                   modifier = Modifier.fillMaxSize(),
                   contentPadding = paddingValues
               ){
                   items(
                       items = listOf( state.data),
                        key = { it.id}
                   )
                   {data->
                       Card(
                           modifier = Modifier
                               .fillMaxWidth()
                               .padding(horizontal = 8.dp)
                               .padding(vertical = 16.dp)

                       ) {
                           if(data!=null) {
                               Text(
                                   text = "Id: ${data.id.uppercase()}\n",
                                   style = MaterialTheme.typography.titleMedium,
                                   modifier = Modifier
                                       .align(Alignment.CenterHorizontally)
                                       .padding(vertical = 8.dp),
                                   fontFamily = FontFamily.Monospace,
                               )
                               Text(
                                   text = "Name: ${data.name}\n",
                                   style = MaterialTheme.typography.titleSmall,
                                   fontFamily = FontFamily.Monospace
                               )
                               Text(
                                   text = "Origin: ${data.origin}",
                                   style = MaterialTheme.typography.titleSmall,
                                   fontFamily = FontFamily.Monospace
                               )
                               Text(
                                   text = "Description: ${data.description}",
                                   style = MaterialTheme.typography.titleSmall,
                                   maxLines = 10,
                                   fontFamily = FontFamily.Monospace
                               )
                               Text(
                                   text = "LifeSpan: ${data.lifeSpan}",
                                   style = MaterialTheme.typography.titleSmall,
                                   maxLines = 10,
                                   fontFamily = FontFamily.Monospace
                               )
                               Text(
                                   text = "Wiki: ${data.wikipediaUrl}",
                                   style = MaterialTheme.typography.titleSmall,
                                   maxLines = 10,
                                   fontFamily = FontFamily.Monospace
                               )
//                               Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                                   PreviewImageFromUrl()
//                               }
                           }
                       }

                       SubcomposeAsyncImage(model = data.image, contentDescription ="Cat picture", modifier = Modifier.fillMaxSize())

                   }
               }
           }

       }
   )
}

@Composable
fun AppIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit,
    contentDescription: String? = null,
    tint: Color = LocalContentColor.current,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = tint,
        )
    }
}

class DetailedStateParameterProvider : PreviewParameterProvider<DetailedState> {
    override val values: Sequence<DetailedState> = sequenceOf(
        DetailedState(
            id = "1",
            fetching = true,
        ),
        DetailedState(
            id="1",
            fetching = false,
            data =DetailedUIModel(id = "0", name = "mjau", description = "its a cat", origin="egypt", lifeSpan = "10-15", wikipediaUrl="a")

        ),
    )
}
@Preview
@Composable
private fun PreviewUserList(
    @PreviewParameter(DetailedStateParameterProvider::class) detailedState: DetailedState,
) {
    DetailedScreen(
        state = detailedState,
        onClose = {},
    )
}