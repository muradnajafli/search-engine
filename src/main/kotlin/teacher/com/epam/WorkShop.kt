package teacher.com.epam

import kotlinx.coroutines.runBlocking

/*
TODO: write a program, which should read user's input and shows the result.
      Main logic is described in Readme.md. There are some additional requirements:
    * your implementation should use [DependencyProvider] to obtain objects.
 */
@kotlinx.coroutines.ExperimentalCoroutinesApi
fun main() = runBlocking {
}

private fun String.isEndProgram(): Boolean = this.equals("exit", ignoreCase = true)