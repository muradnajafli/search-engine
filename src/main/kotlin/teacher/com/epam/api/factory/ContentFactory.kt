package teacher.com.epam.api.factory

import kotlinx.coroutines.flow.Flow
import teacher.com.epam.api.Asset

/** Represents base content factory, which provides concrete type of [Asset]s */
abstract class ContentFactory<out T : Asset> {

    /** Represents movie, live or cast data */
    protected abstract val dataList: Array<*>

    /** Provides flow with concrete content: [Movie], [TvChannel] or [Cast] */
    abstract fun provideContent(): Flow<T>
}

/*
    Movies data:
        | name                                 | release year in unix time
        "Harry Potter and the Sorcerer's Stone"| 1005861600000
        "28 Weeks Later"                       | 1178830800000
        "Beowulf"                              | 1195596000000
        "The Seven Deadly Sins"                | 1416088800000
        "Die Hard"                             | 585345600000
        "Rocky"                                | 217371600000
        "Doctor Strange"                       | 1477342800000
        "Braveheart"                           | 801262800000
        "Beauty and the Beast"                 | 1487800800000
        "Seven"                                | 811717200000

   Tv Channel data:
        | channel number | name
        №1               | "ABC"
        №2               | "NBC"
        №3               | "CBS"
        №4               | "FOX"
        №5               | "PBS"
        №6               | "CW"
        №7               | "National Geographic"
        №8               | "Discovery"
        №9               | "UPN"
        №10              | "BBC"

   Cast data:
       | name              | film count
       "Adriana Ferdynand" | 1
       "Walenty Kuba"      | 2
       "Jarek Franciszka"  | 3
       "Quintella Hayley"  | 4
       "Fraser Starr"      | 5
       "Wallis Chuck"      | 6
       "Nino Avksenti"     | 7
       "Daviti Ketevan"    | 8
       "Ioane Korneli"     | 9
       "Mariami Nika"      | 10
 */