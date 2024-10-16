package rs.raf.rma.catalist.breeds.list

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import rs.raf.rma.catalist.breeds.list.CatsContract.CatsListState
import rs.raf.rma.catalist.breeds.list.model.CatsUIModel
import rs.raf.rma.catalist.ui.theme.BurntSiena
import rs.raf.rma.catalist.ui.theme.Sunglow

fun NavGraphBuilder.breeds(
    route: String,
    onBreedsClick: (String) -> Unit,
    navController: NavController
) = composable(
    route = route
){

    val catsListViewModel = hiltViewModel<CatsViewModel>()
    val state = catsListViewModel.state.collectAsState()
    BreedsListScreen(
        state = state.value,
//        eventPublisher = {
//            catsListViewModel.setEvent(it)
//        },
        onBreedsClick = onBreedsClick
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedsListScreen(
    state: CatsListState,
//    eventPublisher: (uiEvent: CatsListUIEvents) -> Unit,
    onBreedsClick: (String) -> Unit
) {
    var text by remember {
        mutableStateOf("")
    }
    var active by remember {
        mutableStateOf(false)
    }
    Scaffold (
        topBar = {
            MediumTopAppBar(
            title = {
                Text(text = "Catalist", color = Sunglow, fontFamily = FontFamily.Monospace)
                    },

            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = BurntSiena,
            ),

            )
                 },

        containerColor = Color.LightGray,
        content = {paddingValues ->
            Box (   modifier = Modifier.fillMaxSize(),
               contentAlignment = Alignment.BottomCenter,
               ){
                            }
            if (state.loading){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }else{
                LazyColumn (
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = paddingValues,

                ){
                    items(
                        items = state.breeds,
                        key = { it.catId }
                    )
                    {cat->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                                .padding(vertical = 16.dp)
                                .clickable {
                                    onBreedsClick(cat.catId)
                                }
                        ) {
                            Text(text = "Name: ${cat.catId.uppercase()}\n", style = MaterialTheme.typography.titleMedium, modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 8.dp),
                                fontFamily = FontFamily.Monospace,
                            )
                            Text(text = "Alternative name: ${cat.altNames}\n",
                                style = MaterialTheme.typography.titleSmall,
                                fontFamily = FontFamily.Monospace
                                )
                            Text(text = "Temper: ${cat.temperament}",
                                style = MaterialTheme.typography.titleSmall,
                                fontFamily = FontFamily.Monospace
                                )
                            Text(text = "Description: ${cat.description}",
                                style = MaterialTheme.typography.titleSmall,
                                maxLines = 10,
                                fontFamily = FontFamily.Monospace
                                )
                        }
                    }
                }



            }

        },
        bottomBar = (
                {
                    SearchBar(
                        query = text,
                        onQueryChange = { text = it },
                        onSearch = {active = false
                                   CatsContract.CatsListUIEvents.SearchQueryChanged(text)
                                   Log.d("Test", "query changed $text")
                        } ,
                        active = active,
                        onActiveChange = {active = it},
                        ) {}
                })
    )




}

class CatsListStateParameterProvider : PreviewParameterProvider<CatsListState> {
    override val values: Sequence<CatsListState> = sequenceOf(
//        CatsListState(
//            loading = true,
//        ),
//        CatsListState(
//            loading = false,
//        ),
        CatsListState(
            loading = false,
            breeds = listOf(
                CatsUIModel(catId = "mema", breed = "ulicna", altNames = "cikita", description = "abc", temperament = "dobrica, dobra, najbolja, bre"),
                CatsUIModel(catId = "toma", breed = "brdjanin", altNames = "tomi makaroni",description = "abc", temperament = "good"),
                CatsUIModel(catId = "gile", breed = "zarkovo", altNames = "macak ",description = "mjau mjau stil, mjau mjau car, mjau mjau mackice, pravi dar mar", temperament ="good"),
                CatsUIModel(catId = "a", breed = "zarkovo", altNames = "macak ",description = "mjau mjau stil, mjau mjau car, mjau mjau mackice, pravi dar mar", temperament ="good"),
            ),
        ),
    )
}

@Preview
@Composable
private fun PreviewBreedsList(@PreviewParameter(CatsListStateParameterProvider::class) catsListState: CatsListState,){
    BreedsListScreen(state = catsListState,  onBreedsClick = {})
}