package ikigaiworks.imgurapi.api.model

/**
 * GSON Model for a "basic" Imgur response
 *
 * This is the basic response for requests that do not return data. If the POST request has a Basic model it will return the id.
 *
 * See: https://api.imgur.com/models/basic
 */

open class ImgurBasicResponse {

    /**
     * Was the request successful
     */
    /**
     * Was the request successful
     */
    val success: Boolean = false

    /**
     * HTTP Status Code
     */
    /**
     * HTTP Status Code
     */
    val status: Int? = null
}
