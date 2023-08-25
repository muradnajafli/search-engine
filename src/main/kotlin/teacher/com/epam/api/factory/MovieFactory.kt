package teacher.com.epam.api.factory

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import teacher.com.epam.api.Asset.*
import java.util.*

class MovieFactory: ContentFactory<Movie>() {
    override val dataList: Array<Pair<String, Long>> = arrayOf(
        "Harry Potter and the Sorcerer's Stone" to 1005861600000,
        "28 Weeks Later"                        to 1178830800000,
        "Beowulf"                               to 1195596000000,
        "The Seven Deadly Sins"                 to 1416088800000,
        "Die Hard"                              to 585345600000,
        "Rocky"                                 to 217371600000,
        "Doctor Strange"                        to 1477342800000,
        "Braveheart"                            to 801262800000,
        "Beauty and the Beast"                  to 1487800800000,
        "Seven"                                 to 811717200000
    )

    override fun provideContent(): Flow<Movie> {
        return List(dataList.size) { index ->
            Movie(
                label = dataList[index].first,
                releaseYear = Date(dataList[index].second)
            )
        }.asFlow()
    }
}