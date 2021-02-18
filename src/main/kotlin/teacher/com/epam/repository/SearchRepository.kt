package teacher.com.epam.repository

import kotlinx.coroutines.flow.Flow
import teacher.com.epam.api.Asset

/**
 * Represent data layer, which designed to use with user's raw input.
 * All inputs should be converted to [Query]
 */
/*
TODO: add implementation, which will interact with SearchApi and returns the result.
 * Your implementation should hold reference to [SearchApi]
*/
interface SearchRepository {

    suspend fun searchContentAsync(query: Query): Flow<Asset>
}