package teacher.com.epam.repository

import teacher.com.epam.api.SearchApi

/**
 * Represents concrete type of search request to cover
 * all search cases in [SearchApi]
 */
/*
TODO: add all necessary subclasses to satisfy [SearchRepository] contract
      and cover all [SearchApi] cases.
 */
sealed class Query(val input: String) {

}
