package rs.raf.rma.catalist.breeds.repository

import rs.raf.rma.catalist.breeds.api.CatsApi
import rs.raf.rma.catalist.breeds.api.model.CatsApiModel
import rs.raf.rma.catalist.breeds.db.asBreedDBModel
import rs.raf.rma.catalist.db.AppDatabase
import java.io.IOException
import javax.inject.Inject

class CatsRepo @Inject constructor(
    private val catsApi: CatsApi,
    private val database: AppDatabase
) {

//    private val catsApi: CatsApi = retrofit.create(CatsApi::class.java)

    suspend fun fetchAllBreeds():List<CatsApiModel>{
         val breeds = catsApi.getAllBreeds()
        database.breedDao().insertAll(list = breeds.map { it.asBreedDBModel()})
        return breeds
    }

    suspend fun getBreedsById(breedsId: String): CatsApiModel {
       val breed : CatsApiModel
        try {
            breed =catsApi.getBreedById(breedsId = breedsId)

        }catch (error: IOException){
            throw error
        }
        return breed
    }

    fun observeBreed(breedId: String) = database.breedDao().observeBreed(breedId = breedId)

}