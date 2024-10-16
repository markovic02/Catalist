package rs.raf.rma.catalist.breeds.db

import rs.raf.rma.catalist.breeds.api.model.CatsApiModel

fun CatsApiModel.asBreedDBModel():BreedData{
    return BreedData(
        id = this.catId,
        name = this.breed,
        description = this.description,
        origin = this.origin,
        lifeSpan = this.lifeSpan,
        image = this.image!!,
        wikipediaUrl = this.wikipediaUrl,
        altNames = this.altNames!!,
        temperament = this.temperament
    )
}