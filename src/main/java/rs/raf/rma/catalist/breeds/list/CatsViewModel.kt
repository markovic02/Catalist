package rs.raf.rma.catalist.breeds.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rs.raf.rma.catalist.breeds.api.model.CatsApiModel
import rs.raf.rma.catalist.breeds.list.CatsContract.CatsListState
import rs.raf.rma.catalist.breeds.list.model.CatsUIModel
import rs.raf.rma.catalist.breeds.repository.CatsRepo
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class CatsViewModel @Inject constructor(
    private val repository: CatsRepo,
): ViewModel(){

    private val  _state = MutableStateFlow(CatsListState())
    val state = _state.asStateFlow()
    private fun setState(reducer: CatsListState.()-> CatsListState) = _state.update(reducer)

    private val events = MutableSharedFlow<CatsContract.CatsListUIEvents>()
    fun setEvent(event: CatsContract.CatsListUIEvents) = viewModelScope.launch { events.emit(event) }

    init {
        observeEvents()
        observeSearchQuery()
        fetchAllBreeds()
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        viewModelScope.launch {
            events
                .filterIsInstance<CatsContract.CatsListUIEvents.SearchQueryChanged>()
                .debounce(2.seconds) //ne okida se odmah hendler za izmenu
                .collect {

                }
        }
    }
    private fun observeEvents() {
        viewModelScope.launch {
            events.collect {
                when (it) {
                    CatsContract.CatsListUIEvents.ClearSearch -> Unit
                    CatsContract.CatsListUIEvents.CloseSearchMode -> setState { copy(isSearchMode = false) }

                    is CatsContract.CatsListUIEvents.SearchQueryChanged -> {
                        // onValueChange from OutlinedTextField is called for every character

                        // We should handle the query text state update here, but make the api call
                        // or any other expensive call somewhere else where we debounce the text changes
//                        it.query // this should be added to state
                        setState { copy(query = it.query) }
                    }

                }
            }
        }
    }
    private fun fetchAllBreeds() {
        viewModelScope.launch {
            setState { copy(loading = true) }
            try {
                Log.d("Error", "fetch all started")

                val breeds = withContext(Dispatchers.IO) {
                    repository.fetchAllBreeds().map { it.asCatsBreedModel() }
                }
                Log.d("Error", "fetch all finished")
                breeds.forEach{
                    Log.d("Test" , "Dohvaceno: $it")
                }


                setState { copy(breeds = breeds ) }
            } catch (error: Exception) {
                Log.d("Error", error.toString())
            } finally {
                setState { copy(loading = false) }
            }
        }
    }

    suspend fun filteredSearch(input: String): List<CatsUIModel>{
        val filteredList = mutableListOf<CatsUIModel>()

        for (breed in state.value.breeds) {
            if (breed.breed.contains(input, ignoreCase = true)) {
                filteredList.add(breed)
            }
        }

        return filteredList
    }
    //greska
    //manualni converter
    private fun CatsApiModel.asCatsBreedModel() = CatsUIModel(
        catId = this.catId,
        breed = this.breed,
        altNames = this.altNames,
        description = this.description,
        temperament = this.temperament,

    )

}