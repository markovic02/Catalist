//package rs.raf.rma.catalist.networking
//
//import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
//import okhttp3.MediaType.Companion.toMediaType
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import rs.raf.rma.catalist.networking.serialization.AppJson
//import java.util.concurrent.TimeUnit
//
//val okHttpClient = OkHttpClient.Builder()
//    .addInterceptor {
//        val updateRequest = it.request().newBuilder()
//            .addHeader("x-api-key", "live_cB0JrnwCqCRgvA5yFVQuDXpR8EAjovO7qIJswOAWs0rYQUpKgEtko5Vj4NfE7Ksw")
//            .build()
//        it.proceed(updateRequest)
////    }
//    .addInterceptor(
//        HttpLoggingInterceptor().apply {
//            setLevel(HttpLoggingInterceptor.Level.BODY)
//        }
//    )
//    .connectTimeout(30, TimeUnit.SECONDS)
//    .readTimeout(30, TimeUnit.SECONDS)
//    .writeTimeout(30, TimeUnit.SECONDS)
//    .build()
//
//val retrofit: Retrofit = Retrofit.Builder()
//    .baseUrl("https://api.thecatapi.com/v1/")//putanja na koju saljemo zahteve
//    .client(okHttpClient)
//    .addConverterFactory(AppJson.asConverterFactory("application/json".toMediaType()))
//    .build()
