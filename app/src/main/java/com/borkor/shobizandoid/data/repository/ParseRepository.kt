package com.borkor.shobizandoid.data.repository

import com.borkor.shobizandoid.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ParseRepository {
    fun getFlag(): Flow<Resource<Boolean>>
}