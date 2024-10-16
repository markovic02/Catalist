package rs.raf.rma.catalist.breeds.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import rs.raf.rma.catalist.breeds.api.model.Image

@Entity
data class BreedData(
    @PrimaryKey val id: String, //catId
    val name: String,
    val description: String,
    val origin: String,
    val lifeSpan: String = "",
    @Embedded val image: Image,
    val wikipediaUrl: String,
    val altNames: String,
    val temperament: String
)

