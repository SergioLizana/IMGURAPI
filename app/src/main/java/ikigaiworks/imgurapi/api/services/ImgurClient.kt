package ikigaiworks.imgurapi.api.services

import ikigaiworks.imgurapi.api.model.ImgurImagesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ImgurClient{
    @GET("account/{username}/images/{page}")
    fun imagesForUser(@Path("username") username: String, @Path("page") page: Int?): Call<ImgurImagesResponse>

}