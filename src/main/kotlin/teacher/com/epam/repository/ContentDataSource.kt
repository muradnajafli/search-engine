package teacher.com.epam.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import teacher.com.epam.api.Asset
import teacher.com.epam.api.SearchApi

@ExperimentalCoroutinesApi
class ContentDataSource(private val searchApi: SearchApi): SearchRepository {
    override suspend fun searchContentAsync(query: Query): Flow<Asset> {
        return when (query) {
            is Query.TypedContains -> searchApi.searchByContains(query.input, query.type)
            is Query.TypedStartedWith -> searchApi.searchByStartWith(query.input, query.type)
            is Query.RawContains -> searchApi.searchByContains(query.input)
            is Query.RawStartedWith -> searchApi.searchByStartWith(query.input)
        }
    }
}