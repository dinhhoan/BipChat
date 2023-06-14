package bigcat.lab.pipchat.domain.network

import androidx.viewbinding.BuildConfig
import bigcat.lab.pipchat.domain.repository.shared.SharedPreferenceRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author hoantd on 8/23/22
 **/
@InstallIn(SingletonComponent::class)
@Module
class RestModule {

    companion object {
        var apiInstance: APIs? = null
        private const val DEFAULT_TIME_OUT = 5
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        localRepository: SharedPreferenceRepository
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.interceptors().add(Interceptor { chain ->
            val original = chain.request()
            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                .method(original.method, original.body)
            requestBuilder.addHeader("Content-Type", "application/json")
            requestBuilder.addHeader(
                "Authorization",
                "Bearer ${localRepository.getTokenUser()}"
            )
            val request = requestBuilder.build()
            chain
                .withConnectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .withWriteTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .withReadTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .proceed(request)
        })

        clientBuilder.protocols(Collections.singletonList(Protocol.HTTP_1_1))
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(logging)
        }

        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        jsonParser: Json
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("Hoan.com.vn")
            .addConverterFactory(
                jsonParser.asConverterFactory("application/json".toMediaType())
            ).build()
    }

    @Provides
    @Singleton
    fun provideJsonParser(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): APIs {
        return retrofit.create(APIs::class.java).also {
            apiInstance = it
        }
    }
}

