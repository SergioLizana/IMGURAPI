package ikigaiworks.imgurapi.login

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import android.webkit.*
import ikigaiworks.imgurapi.SECRET_IMGUR_APP_CALLBACK_URL
import ikigaiworks.imgurapi.api.model.ImgurAccessToken

class LoginWebView : WebViewClient {

    var tokenCallback: LoginCallback? = null
    var context: Context
    private var handledAccessToken: ImgurAccessToken? = null


    constructor(tokenCallback: LoginCallback, context: Context?) {
        this.tokenCallback = tokenCallback
        this.context = context!!
    }

    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest?): Boolean {
        if (request != null) {
            val requestUri = request.url
            Log.v("imgurWebView", "request: " + requestUri.toString())
            if (isAppRedirect(requestUri)) {
                Log.v("imgurWebView", "We need to handle this URL ourselves")
                handleAppRedirect(view, requestUri)
            } else {
                Log.v("imgurWebView", "Not the app redirect")
            }
        }

        return super.shouldOverrideUrlLoading(view, request)
    }

    /**
     * Determines if the page represented by the given Uri is our app redirect URL.
     * @param url The Uri to check
     * @return True if the Uri represents our app redirect URL, false otherwise.
     */
    private fun isAppRedirect(url: Uri?): Boolean {
        if (url == null) {
            return false
        }

        // This is our app redirect if the Uri without the fragment/hash equals our app redirect URL
        // but the fragment isn't empty (since that is where the token information will be stored)
        val urlNoFragment = url.buildUpon().fragment("").build()
        val fragment = url.fragment

        return urlNoFragment.toString() == SECRET_IMGUR_APP_CALLBACK_URL && !TextUtils.isEmpty(fragment)
    }

    private fun handleAppRedirect(view: WebView, url: Uri) {
        if(view !=null) {
            val fragment = url.fragment
            // parse the access token information from the fragment/hash
            val accessToken = ImgurAccessToken.parseFromFragment(fragment)

            // on API higher than 21, both signatures for shouldOverrideUrlLoading get called
            // only handle the first one
            if (handledAccessToken == null || !accessToken.equals(handledAccessToken)) {
                handledAccessToken = accessToken

                // stop the WebView from loading the app redirect URL (since we don't need the
                // page to load to handle it).
                view.stopLoading()
                view.loadUrl("about:blank")

                // pass our access token to our fragment
                try {
                    tokenCallback?.OnImgurTokenReceived(accessToken)
                } catch (e: Exception) {
                    Log.v("handleAppRedirect", "should implement tokenCallback")
                }

            }
        }else{
            Log.v("handleAppRedirect", "WebView cannot be null")
        }
    }

}