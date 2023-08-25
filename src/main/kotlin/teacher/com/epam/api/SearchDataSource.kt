package teacher.com.epam.api

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import teacher.com.epam.api.factory.CastFactory
import teacher.com.epam.api.factory.MovieFactory
import teacher.com.epam.api.factory.TvChannelFactory

@ExperimentalCoroutinesApi
class SearchDataSource(
    private val movieFactory: MovieFactory,
    private val castFactory: CastFactory,
    private val tvChannelFactory: TvChannelFactory
): SearchApi {
    override suspend fun searchByContains(query: String): Flow<Asset> {
        return mergeAll().onEach { delay(100L) }
            .filter { it.getPoster().contains(query, true) }
    }

    override suspend fun searchByContains(query: String, type: Asset.Type): Flow<Asset> {
        return type.toFlow().onEach { delay(100L) }
            .filter { it.getPoster().contains(query, true ) }
    }

    override suspend fun searchByStartWith(query: String): Flow<Asset> {
        return mergeAll().onEach { delay(100L) }
            .filter { it.getPoster().startsWith(query, true) }
    }

    override suspend fun searchByStartWith(query: String, type: Asset.Type): Flow<Asset> {
        return type.toFlow().onEach { delay(100L) }
            .filter { it.getPoster().startsWith(query, true) }
    }

    private fun mergeAll() = merge(
        movieFactory.provideContent(),
        tvChannelFactory.provideContent(),
        castFactory.provideContent()
    )
    private fun Asset.Type.toFlow(): Flow<Asset> {
        return when (this) {
            Asset.Type.VOD -> movieFactory.provideContent()
            Asset.Type.LIVE -> tvChannelFactory.provideContent()
            Asset.Type.CREW -> castFactory.provideContent()
        }
    }

}