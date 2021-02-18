package teacher.com.epam

import io.kotlintest.matchers.collections.shouldBeEmpty
import io.kotlintest.matchers.collections.shouldContainAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test
import teacher.com.epam.api.Asset
import teacher.com.epam.engine.SearchResult
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
internal class EngineTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)
    private val engine = DependencyProvider.provideEngine(testDispatcher)

    @After
    fun cleanUp() {
        testDispatcher.cleanupTestCoroutines()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `input 'a' (contains) should return all types of assets`() = testScope.runBlockingTest {
        val testQuery = "a"
        val result: List<SearchResult> = invokeRequest(testQuery)
        val type = result.map { it.type }
        type.shouldContainAll(listOf(Asset.Type.VOD, Asset.Type.LIVE, Asset.Type.CREW))
    }

    @Test
    fun `input 'as' (startWith) should return empty list`() = testScope.runBlockingTest {
        val testQuery = "@as"
        val result = invokeRequest(testQuery)
        result.shouldBeEmpty()
    }

    @Test
    fun `input 'se' (contains, type VOD) should return 2 movies`() = testScope.runBlockingTest {
        val testQuery = "se?VOD"
        val result = invokeRequest(testQuery)
        assertTrue("Only VOD assets should be bound") {
            result.map { it.type }.all { it == Asset.Type.VOD }
        }
    }

    @Test
    fun `input 'an' (contains, type LIVE) should return 2 tv channels`() = testScope.runBlockingTest {
        val testQuery = "an?LIVE"
        val result = invokeRequest(testQuery)
        assertTrue("Two live content assets should be found") {
            result.map { it.type }.all { it == Asset.Type.LIVE }
        }
    }

    @Test
    fun `input 'an' (startWith, type LIVE) should return only 1 tv channels`() = testScope.runBlockingTest {
        val testQuery = "@AB?LIVE"
        val result = invokeRequest(testQuery)
        assertTrue("Only ONE LIVE content item should be found") {
            result.size == 1 && result.first().type == Asset.Type.LIVE
        }
    }

    @Test
    fun `input 'BC' (contains) should return only 2 tv channels (ABC, NBC, and BBC)`() = testScope.runBlockingTest {
        val testQuery = "BC"
        val result = invokeRequest(testQuery)
        val expectedTitles = listOf("ABC", "NBC", "BBC")
        val actualTitles = result.flatMap { searchResult -> searchResult.assets.map { it.getPoster() } }
        assertTrue("Only ONE LIVE content item should be found") {
            expectedTitles == actualTitles
        }
    }

    @Test
    fun `input '1' (contains) should not find The Seven Deadly Sins`() = testScope.runBlockingTest {
        val testQuery = "1"
        val result = invokeRequest(testQuery)
        val expectedResult: List<Asset> = result
            .flatMap { search -> search.assets }
            .filter { asset -> asset.getPoster() == "The Seven Deadly Sins" && asset.type == Asset.Type.LIVE }
        assertTrue("'№2. Football 1' should be found for input '1'") {
            expectedResult.isEmpty()
        }
    }

    @Test
    fun `input '2' (contains) should not find Football 1`() = testScope.runBlockingTest {
        val testQuery = "2"
        val result = invokeRequest(testQuery)
        val expectedResult: List<Asset> = result
            .flatMap { search -> search.assets }
            .filter { asset -> asset.getPoster() == "Football 1" && asset.type == Asset.Type.LIVE }
        assertTrue("'№2. Football 1' should be found for input '2'") {
            expectedResult.isEmpty()
        }
    }

    @Test
    fun `input '3' (contains) should not find Jarek Franciszka`() = testScope.runBlockingTest {
        val testQuery = "3"
        val result = invokeRequest(testQuery)
        val expectedResult: List<Asset> = result
            .flatMap { search -> search.assets }
            .filter { asset -> asset.getPoster() == "Jarek Franciszka" && asset.type == Asset.Type.CREW }
        assertTrue("'Jarek Franciszka (3 films)' should be found for input '3'") {
            expectedResult.isEmpty()
        }
    }

    private suspend fun invokeRequest(testQuery: String) =
        engine.searchContentAsync(testQuery).toList(mutableListOf())
}