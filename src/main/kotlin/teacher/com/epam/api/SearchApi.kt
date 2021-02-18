package teacher.com.epam.api

import kotlinx.coroutines.flow.Flow

/**
 * Let's imagine that, this is some library's API with complied code,
 * and you have no access to this code. Class, witch will implement this
 * contract, should takes care about whole logic.
 *
 * Notice, that function with [Asset.Type] in arguments MUST search
 * only a specific (matching) [ContentFactory].
 * For example:
 * query with type [Asset.Type.VOD] must check only content factory which provides ONLY movies.
 */
/*
TODO:
 * add implementation, which will interact with all content
   factories and returns the appropriate result.
 * functions with [Asset.Type] in arguments MUST search
   only in specific (matching) [ContentFactory].
   For example:
   query with type [Asset.Type.VOD] must check content factory which provides ONLY movies.
 */
interface SearchApi {

    /**
     * Searches for assets, whose poster **contains** the [query]
     * @return flow with results.
     */
    suspend fun searchByContains(query: String): Flow<Asset>

    /**
     * Searches for assets of the given [type], whose poster **contains** the [query]
     * @return flow with results.
     */
    suspend fun searchByContains(query: String, type: Asset.Type): Flow<Asset>

    /**
     * Searches for assets, whose poster **starts with** the [query]
     * @return flow with results.
     */
    suspend fun searchByStartWith(query: String): Flow<Asset>

    /**
     * Searches for assets of the given [type], whose poster **starts with** the [query]
     * @return flow with results.
     */
    suspend fun searchByStartWith(query: String, type: Asset.Type): Flow<Asset>
}