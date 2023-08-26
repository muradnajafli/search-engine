package teacher.com.epam.repository

import teacher.com.epam.api.Asset
import teacher.com.epam.api.SearchApi

/**
 * Represents concrete type of search request to cover
 * all search cases in [SearchApi]
 */
sealed class Query(val input: String) {
    class TypedContains(input: String, val type: Asset.Type ): Query(input)
    class TypedStartedWith(input: String, val type: Asset.Type ): Query(input)
    class RawContains(input: String): Query(input)
    class RawStartedWith(input: String): Query(input)


}
