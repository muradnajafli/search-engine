package teacher.com.epam.engine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import teacher.com.epam.repository.SearchRepository

/**
 * In Clean Architecture this class should be named as UseCase or Interactot.
 * All necessary logic should be implemented here.
 */
/*
TODO: implement all business logic which converts input stream to
      and then used to in [SearchRepository]
 * your implementation should receive CoroutineDispatcher in constructor
   (it gives possibility to write tests)
 * flow should executed on provided CoroutineDispatcher
 */
class SearchEngine(
    private val repository: SearchRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun searchContentAsync(rawInput: String): Flow<SearchResult> = TODO()
}
