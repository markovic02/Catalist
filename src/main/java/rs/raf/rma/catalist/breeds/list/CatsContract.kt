package rs.raf.rma.catalist.breeds.list

import rs.raf.rma.catalist.breeds.list.model.CatsUIModel

interface CatsContract{

    data class CatsListState(
        val loading: Boolean = true,
        val query: String = "",
        val isSearchMode: Boolean = false,
        val breeds: List<CatsUIModel> = emptyList(),
        val filteredSearch: List<CatsUIModel> = emptyList(),
    )

    sealed class CatsListUIEvents {
        data class SearchQueryChanged(val query:String) : CatsListUIEvents()
        data object ClearSearch: CatsListUIEvents()
        data object CloseSearchMode: CatsListUIEvents()
    }

}