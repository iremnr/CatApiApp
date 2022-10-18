package com.example.catapiapp.di

import com.example.catapiapp.BuildConfig
import com.example.catapiapp.network.ApiFactory
import com.example.catapiapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


//HTTP isteği atabilmemiz için retrofitle API factory' u oluşturmamız gerekir.
//Bunu otomatikleştirebilmek için, biz içine parametre vermeden kendisi hangi parametre eksikse onu tamamlayabilecek bir modul oluşturulmuştur.

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //Intercepter bir http isteği atıldığında isteğin ve cevabın kaç saniyede gittiğini ve gelen cevabın kaç saniyede geldiğini döndüren fonksiyndur.

    @Singleton
    @Provides
    fun provideHttpLoggerInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideHttpClint(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS).addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build()
    }
    //Retrofit objesi oluşturuldu

    @Singleton
    @Provides
    fun provideApiFactory(retrofit: Retrofit): ApiFactory {
        return retrofit.create(ApiFactory::class.java)
    }
    //API factory oluşturuldu


}