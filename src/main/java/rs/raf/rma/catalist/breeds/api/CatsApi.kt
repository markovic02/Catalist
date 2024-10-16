package rs.raf.rma.catalist.breeds.api

import retrofit2.http.GET
import retrofit2.http.Path
import rs.raf.rma.catalist.breeds.api.model.CatsApiModel

/*
Ruta za dobijanje liste rasa:
GET https://api.thecatapi.com/v1/breeds
Ruta za dobijanje detalja o konkretnoj rasi:
GET https://api.thecatapi.com/v1/breeds/{breed_id}
Ruta za dobijanje informacija o specifičnoj fotografiji:
GET https://api.thecatapi.com/v1/images/{image_id}
Ruta za dobijanje fotografija o rasi:
GET https://api.thecatapi.com/v1/images/search?breed_ids=id
Ruta za pretraživanje rasa:
GET https://api.thecatapi.com/v1/breeds/search?q=query
 */
interface CatsApi {

    @GET("breeds")
    suspend fun getAllBreeds():List<CatsApiModel>

    @GET("breeds/{breedsId}")
    suspend fun getBreedById(@Path("breedsId") breedsId: String,): CatsApiModel

//    @GET("images/{image_id}")
//    suspend fun getImageById(@Path("image_id") imageId: Int):


}