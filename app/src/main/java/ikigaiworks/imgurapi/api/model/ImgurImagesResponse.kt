package ikigaiworks.imgurapi.api.model


/**
 * GSON Model for a "images" Imgur response
 *
 * This is the response that will be returned when making a request for the user's images.
 */

class ImgurImagesResponse : ImgurBasicResponse() {

    /**
     * List of ImgurImage objects
     */
    /**
     * List of ImgurImage objects
     */
    val data: List<ImgurImage>? = null
}
