package rs.raf.rma.catalist.breeds.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface BreedDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(breedData: BreedData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list:List<BreedData>)

    @Query("SELECT * FROM breeddata")
    suspend fun getAll(): List<BreedData>

    @Query("SELECT * FROM BreedData WHERE BreedData.id=:breedId")
    fun observeBreed(breedId: String): Flow<List<BreedData>>

//    @Query("SELECT * FROM breeddata WHERE id = :id")
//    suspend fun getById(id: String)
}