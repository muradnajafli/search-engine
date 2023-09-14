package teacher.com.epam.engine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import teacher.com.epam.api.Asset
import teacher.com.epam.repository.Query
import teacher.com.epam.repository.SearchRepository

/**
 * In Clean Architecture this class should be named as UseCase or Interactor.
 * All necessary logic should be implemented here.
 */
class SearchEngine(
    private val repository: SearchRepository,
    private val dispatcher: CoroutineDispatcher
) {
    private val regex: Regex = Regex("(\\?\\w{3,4}\$)")

    fun searchContentAsync(rawInput: String): Flow<SearchResult> {
        validateInput(rawInput)
        val matchResult: MatchResult? = regex.find(rawInput)
        val type = matchResult?.value.toAssetType()
        val input = type?.let { rawInput.removeRange(matchResult!!.range) } ?: rawInput
        val query = input.toQuery(type)

        return repository.searchContentAsync(query)
            .flowOn(dispatcher)
            .map { asset -> SearchResult(
                listOf(asset),
                asset.type, asset.type.toGroupName()) }}
    }
    private fun validateInput(rawInput: String) {
        require(rawInput != "@" && rawInput.isNotBlank()) { "Incorrect input" }
    }
    private fun String?.toAssetType(): Asset.Type? {
        return this?.drop(1)?.toUpperCase()?.let {
            Asset.Type.valueOf(it)
        }
    }
    private fun String.toQuery(assetType: Asset.Type?): Query {
        return if (startsWith("@")) {
            when (assetType) {
                null -> Query.RawStartedWith(drop(1))
                else -> Query.TypedStartedWith(drop(1), assetType)
            }

        } else {
            when (assetType) {
                null -> Query.RawContains(this)
                else -> Query.TypedContains(this, assetType)
            }
        }

    }

private fun Asset.Type.toGroupName(): String {
    return when (this) {
        Asset.Type.VOD -> "-- Movies --"
        Asset.Type.LIVE -> "-- TvChannels --"
        Asset.Type.CREW -> "-- Cast and Crew --"
    }
}
