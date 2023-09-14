package teacher.com.epam.api.factory

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import teacher.com.epam.api.Asset.*

class CastFactory: ContentFactory<Cast>() {
    override val dataList: Array<String> = arrayOf(
        "Adriana Ferdynand",
        "Walenty Kuba",
        "Jarek Franciszka",
        "Quintella Hayley",
        "Fraser Starr",
        "Wallis Chuck",
        "Nino Avksenti",
        "Daviti Ketevan",
        "Ioane Korneli",
        "Mariami Nika"
    )

    override fun provideContent(): Flow<Cast> {
        return List(dataList.size) { index ->
            val (name, surname) = dataList[index].split(" ")
            Cast(
                name = name,
                surname = surname,
                filmCount = index + 1
            )
        }.asFlow()
    }
}