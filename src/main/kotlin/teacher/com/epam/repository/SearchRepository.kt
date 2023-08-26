package teacher.com.epam.repository

import kotlinx.coroutines.flow.Flow
import teacher.com.epam.api.Asset

/**
 * Represent data layer, which designed to use with user's raw input.
 * All inputs should be converted to [Query]
 */

interface SearchRepository {

    fun searchContentAsync(query: Query): Flow<Asset>
}