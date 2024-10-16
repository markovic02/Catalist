package rs.raf.rma.catalist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import rs.raf.rma.catalist.breeds.db.BreedDao
import rs.raf.rma.catalist.breeds.db.BreedData

@Database(
    entities = [
        BreedData::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase: RoomDatabase(){
    abstract fun breedDao(): BreedDao
}