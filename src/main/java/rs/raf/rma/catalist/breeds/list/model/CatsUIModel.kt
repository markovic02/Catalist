package rs.raf.rma.catalist.breeds.list.model

/*
Stavke liste (list items) treba da sadrže:
ime rase,
alternativna imena rase ukoliko postoje,
opis rase (maksimalno 250 karaktera opisa; skratiti ukoliko je opis duži),
najviše tri osobine temperamenta (za rase koje imaju više osobina, nasumično izabrati tri). (predlog: možete koristiti chips)
 */
data class CatsUIModel (
    val catId: String,
    val breed: String,
    val altNames: String? = null,
    val description: String,
    val temperament: String,

)

