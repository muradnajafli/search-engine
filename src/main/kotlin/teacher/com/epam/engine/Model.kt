package teacher.com.epam.engine

import teacher.com.epam.api.Asset

/**
 * Represents overall result from the [SearchEngine]
 */
data class SearchResult(
    val assets: List<Asset>,
    val type: Asset.Type,
    /** Designed to use in terminal. See screenshot (output_example.png) in project root dir for more info*/
    val groupName: String
)