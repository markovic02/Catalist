package rs.raf.rma.catalist.breeds.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CatsModule {

    @Provides
    @Singleton
    fun provideCatsApi(retrofit: Retrofit): CatsApi = retrofit.create()
}