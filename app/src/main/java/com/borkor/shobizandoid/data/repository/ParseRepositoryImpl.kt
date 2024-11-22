package com.borkor.shobizandoid.data.repository

import android.util.Log
import com.borkor.shobizandoid.utils.Resource
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParseRepositoryImpl @Inject constructor(
) : ParseRepository {
    override fun getFlag(): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.loading())
            val parseQuery = ParseQuery.getQuery<ParseObject>("AppSettings")
            val appSettings = parseQuery.get("ZKFQGHhkn8")
            emit(Resource.success(data = appSettings.getBoolean("flag")))
        }.catch { e ->
            emit(Resource.error(e))
        }
        /*return flow {
            val query = ParseQuery.getQuery<ParseObject>("AppSettings")
            val appSettings = query.find()
            val flag = appSettings[0].getBoolean("flag")
            if (appSettings.isNotEmpty()){
                emit(Resource.success(data = flag))
            }
        }*/
    }
}