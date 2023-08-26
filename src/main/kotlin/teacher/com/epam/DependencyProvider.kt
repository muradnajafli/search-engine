package teacher.com.epam

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import teacher.com.epam.api.SearchApi
import teacher.com.epam.api.SearchDataSource
import teacher.com.epam.api.factory.CastFactory
import teacher.com.epam.api.factory.MovieFactory
import teacher.com.epam.api.factory.TvChannelFactory
import teacher.com.epam.engine.SearchEngine
import teacher.com.epam.repository.ContentDataSource
import teacher.com.epam.repository.SearchRepository

/**
 * This is a simple implementation of Service Locator pattern.
 * It uses 'fabric' pattern to provide all classes
 * you need in one place.
 * More to read : [link](https://en.wikipedia.org/wiki/Service_locator_pattern)
 */
@ExperimentalCoroutinesApi
object DependencyProvider {

    fun provideEngine(dispatcher: CoroutineDispatcher): SearchEngine {
        return SearchEngine(
            repository = provideRepository(),
            dispatcher = dispatcher
        )
    }

    private fun provideRepository(): SearchRepository {
        return ContentDataSource(searchApi = provideApi())
    }

    private fun provideApi(): SearchApi {
        return SearchDataSource(
            movieFactory = MovieFactory(),
            castFactory = CastFactory(),
            tvChannelFactory = TvChannelFactory()
        )
    }
}