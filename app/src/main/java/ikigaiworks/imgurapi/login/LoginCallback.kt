package ikigaiworks.imgurapi.login

interface LoginCallback {
    abstract fun OnImgurTokenReceived(accessToken: ImgurAccessToken)
}