package rs.raf.rma.catalist.detailed

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rs.raf.rma.catalist.breeds.api.model.CatsApiModel
import rs.raf.rma.catalist.breeds.repository.CatsRepo
import rs.raf.rma.catalist.navigation.breedId
import javax.inject.Inject

@HiltViewModel
class DetailedViewModel @Inject constructor (
   savedStateHandle: SavedStateHandle,
    private val repository: CatsRepo,
):ViewModel(){

    private val id: String = savedStateHandle.breedId
    private val _state = MutableStateFlow(DetailedState(id = id))
    val state = _state.asStateFlow()
    private fun setState(reducer: DetailedState.()-> DetailedState) = _state.getAndUpdate(reducer)

//    private val events = MutableSharedFlow<>
    init {
        fetchDetails()
//        observeEvents()
    }

//    private fun observeBreed(){
//        viewModelScope.launch {
//            setState { copy(fetching = true) }
//            repository.observeBreed(breedId = id)
//                .distinctUntilChanged()
//                .collect{
//                    setState { copy(data = (it.map { it })) }
//                }
//        }
//    }
    private fun fetchDetails(){
        viewModelScope.launch {
            setState { copy(fetching = true) }
            Log.d("Test", "dohvatanje detalja o rasi...")

            try{
                val details = withContext(Dispatchers.IO){
                    repository.getBreedsById(breedsId = id) //dohvatimo sve detalje o odredjenoj rasi
                }
                setState { copy(data = details.asDetailedState() ) } //konvertujemo detalje
                setState { (copy(fetching = false)) } //zavrsimo fetching state
            }catch (error: Exception){
            Log.d("Error", "error u fetchDetails ${error.toString()}")
            }
            setState { copy(fetching = false) }

        }
    }

    private fun CatsApiModel.asDetailedState() : DetailedUIModel{
     Log.d("CATAPI" ,"${image?.url}")
      return DetailedUIModel(
          id = this.catId,
        name = this.breed,
        description = this.description,
        origin = this.origin,
        lifeSpan =  this.lifeSpan,
        wikipediaUrl = this.wikipediaUrl,
        image = this.image?.url
      )
    }



}