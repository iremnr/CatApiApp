package com.example.catapiapp.ui

import com.example.catapiapp.base.BaseRepository
import com.example.catapiapp.network.ApiFactory
import javax.inject.Inject

class ObserveRepository @Inject constructor(private val apiFactory: ApiFactory) : BaseRepository() {

    suspend fun getData() = safeApiRequest {
        apiFactory.getData()
    }
}