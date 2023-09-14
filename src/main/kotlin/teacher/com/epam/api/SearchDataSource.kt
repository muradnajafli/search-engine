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
    override fun searchByContains(query: String): Flow<Asset> {
        return mergeContent().delayOnEach()
            .filter { it.getPoster().contains(query, true) }
    }

    override fun searchByContains(query: String, type: Asset.Type): Flow<Asset> {
        return getTypeFlow(type).delayOnEach()
            .filter { it.getPoster().contains(query, true ) }
    }

    override fun searchByStartWith(query: String): Flow<Asset> {
        return mergeContent().delayOnEach()
            .filter { it.getPoster().startsWith(query, true) }
    }

    override fun searchByStartWith(query: String, type: Asset.Type): Flow<Asset> {
        return getTypeFlow(type).delayOnEach()
            .filter { it.getPoster().startsWith(query, true) }
    }

    private fun mergeContent() = merge(
        movieFactory.provideContent(),
        tvChannelFactory.provideContent(),
        castFactory.provideContent()
    )
    private fun getTypeFlow(type: Asset.Type): Flow<Asset> {
        return when (type) {
            Asset.Type.VOD -> movieFactory.provideContent()
            Asset.Type.LIVE -> tvChannelFactory.provideContent()
            Asset.Type.CREW -> castFactory.provideContent()
        }
    }
    private fun Flow<Asset>.delayOnEach() = onEach { delay(100L) }
}