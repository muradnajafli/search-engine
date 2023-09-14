package teacher.com.epam.api.factory

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import teacher.com.epam.api.Asset.*

class TvChannelFactory : ContentFactory<TvChannel>() {
    override val dataList: Array<String> = arrayOf(
        "ABC",
        "NBC",
        "CBS",
        "FOX",
        "PBS",
        "CW",
        "National Geographic",
        "Discovery",
        "UPN",
        "BBC"
    )

    override fun provideContent(): Flow<TvChannel> {
        return List(dataList.size) { index ->
            val label = dataList[index]
            TvChannel(
                label = label,
                number = index+1
            )
        }.asFlow()
    }

}