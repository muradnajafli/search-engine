package teacher.com.epam

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import teacher.com.epam.api.SearchApi
import teacher.com.epam.engine.SearchEngine
import teacher.com.epam.repository.SearchRepository

/**
 * This is a simple implementation of Service Locator pattern.
 * It uses 'fabric' pattern to provide all classes
 * you need in one place.
 * More to read : [link](https://en.wikipedia.org/wiki/Service_locator_pattern)
 */
//TODO: add your implementation of each contract in this task
@ExperimentalCoroutinesApi
object DependencyProvider {

    fun provideEngine(dispatcher: CoroutineDispatcher): SearchEngine = TODO()

    private fun provideRepository(): SearchRepository = TODO()

    private fun provideApi(): SearchApi = TODO()
}