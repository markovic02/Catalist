package rs.raf.rma.catalist.detailed
/*
minimalno jednu sliku rase,
ime rase,
kompletan opis rase,
spisak zemalja porekla,
sve osobine temperamenta,
životni vek,
prosečna težina i/ili visina rase,
minimum 5 UI widgeta o ponašanju i potrebama rase po izboru autora.
Iskoristiti sledeća polja iz api modela: ("adaptability": 5, "affection_level": 5, "child_friendly": 4, "dog_friendly": 5, "energy_level": 4, "grooming": 1, "health_issues": 3, "intelligence": 5, "shedding_level": 3, "social_needs": 5, "stranger_friendly": 5, "vocalisation": 5)
da li je retka vrsta,
dugme koje otvara stranicu o rasi na Vikipediji u browseru

 */
//https://cdn2.thecatapi.com/images/ api za dohvtanje slike
//  "id": "ozEvzdVM-",
//  "url": "https://cdn2.thecatapi.com/images/ozEvzdVM-.jpg",
//uz reference id odemo na aipi+refid i u polju url imamo sliku
data class DetailedState(
    val id: String,
    val fetching: Boolean = false,
    val data: DetailedUIModel = DetailedUIModel(),
    var error: DetailsError? = null
)
sealed class DetailsError {
    data class DataUpdateFailed(val cause: Throwable? = null) : DetailsError()
}

