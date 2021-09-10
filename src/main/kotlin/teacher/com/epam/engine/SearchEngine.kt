package teacher.com.epam.engine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import teacher.com.epam.api.Asset
import teacher.com.epam.repository.SearchRepository

/**
 * In Clean Architecture this class should be named as UseCase or Interactor.
 * All necessary logic should be implemented here.
 */
/*
TODO: implement all business logic which converts input stream to [Query]
      and then used to in [SearchRepository]
 * your implementation should receive CoroutineDispatcher in constructor
   (it gives possibility to write tests)
 * flow should be executed on provided CoroutineDispatcher
 */
class SearchEngine(
    private val repository: SearchRepository,
    private val dispatcher: CoroutineDispatcher
) {
    private val regex: Regex = Regex("(\\?\\w{3,4}\$)")

    suspend fun searchContentAsync(rawInput: String): Flow<SearchResult> = TODO()
}

private fun Asset.Type.toGroupName(): String {
    return when (this) {
        Asset.Type.VOD -> "-- Movies --"
        Asset.Type.LIVE -> "-- TvChannels --"
        Asset.Type.CREW -> "-- Cast and Crew --"
    }
}
