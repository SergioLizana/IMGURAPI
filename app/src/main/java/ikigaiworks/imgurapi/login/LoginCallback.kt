package ikigaiworks.imgurapi.login

import ikigaiworks.imgurapi.api.model.ImgurAccessToken

interface LoginCallback {
    fun OnImgurTokenReceived(accessToken: ImgurAccessToken)
}