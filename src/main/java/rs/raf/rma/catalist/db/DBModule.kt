package rs.raf.rma.catalist.db

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DBModule {
    @Singleton
    @Provides
    fun provideDatabase(builder: AppDatabaseBuilder): AppDatabase {
        return builder.build()
    }
}