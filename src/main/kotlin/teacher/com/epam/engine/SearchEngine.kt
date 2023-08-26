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

    fun searchContentAsync(rawInput: String): Flow<SearchResult> {
        validateInput(rawInput){ message -> error(message) }
        val matchResult: MatchResult? = regex.find(rawInput)
        val type = matchResult?.value.toAssetType()
        val input = type?.run { rawInput.removeRange(matchResult!!.range) } ?: rawInput
        val query = input.toQuery(type)

        return repository.searchContentAsync(query)
            .flowOn(dispatcher)
            .map { SearchResult(listOf(it), it.type, it.type.toGroupName()) }}
    }
    private inline fun validateInput(rawInput: String, block: (String) -> Nothing) {
        val message = when {
            rawInput == "@" -> "Incorrect input"
            rawInput.isEmpty() -> "Input is empty"
            rawInput.isBlank() -> "Input is blank"
            else -> null
        }
        message?.let(block)
    }
    private fun String?.toAssetType(): Asset.Type? {
        return this?.drop(1)?.capitalize()?.let {
            Asset.Type.valueOf(it)
        }
    }
    private fun String.toQuery(type: Asset.Type?): Query {
        return if (startsWith("@")) {
            when (type) {
                null -> Query.RawStartedWith(drop(1))
                else -> Query.TypedStartedWith(drop(1), type)
            }

        } else {
            when (type) {
                null -> Query.RawContains(this)
                else -> Query.TypedContains(this, type)
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
