@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package teacher.com.epam

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.runBlocking
import teacher.com.epam.engine.SearchEngine

/*
TODO: write a program, which should read user's input and shows the result.
      Main logic is described in Readme.md. There are some additional requirements:
    * your implementation should use [DependencyProvider] to obtain objects.
 */
@ExperimentalCoroutinesApi
var isRunning = true

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {

    val engine = DependencyProvider.provideEngine(Dispatchers.IO)

    println(
        "$TITLE_BORDER $GREETINGS_MESSAGE $TITLE_BORDER $HINT_MESSAGE"
    )
    while (isRunning) {
        print("TYPE REQUEST: ")
        val query = readLine().orEmpty()
        when {
            query.isEndProgram() -> proceedExit()
            else -> proceedSearch(query, engine)
        }
    }
}

@ExperimentalCoroutinesApi
private suspend fun proceedSearch(query: String, repository: SearchEngine) {
    try {
        println("Start searching...")
        repository.searchContentAsync(query)
            .onEmpty { println("Sorry, but we found nothing") }
            .collectIndexed { index, result ->
                if (index == 0) {
                    println("Result is:")
                }
                println(result.groupName)
                result.assets.forEach { println(it.toString()) }
            }

    } catch (error: Throwable) {
        proceedError(error)
    }
}

fun proceedError(error: Throwable) {
    val errorMessage = when (error) {
        is IllegalArgumentException -> "Invalid asset type in request"
        else -> error.message ?: "Something went wrong"
    }
    println("$errorMessage. Please, try again")
}

@OptIn(ExperimentalCoroutinesApi::class)
private fun proceedExit() {
    isRunning = false
    println("Thank yot for choosing out service. See you next time")
}

private fun String.isEndProgram(): Boolean = this.equals("exit", ignoreCase = true)