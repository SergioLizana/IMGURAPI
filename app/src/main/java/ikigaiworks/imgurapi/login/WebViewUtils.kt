package ikigaiworks.imgurapi.login

import android.net.Uri
import ikigaiworks.imgurapi.ARG_IMGUR_APP_ID
import ikigaiworks.imgurapi.OAUTH_AUTHORIZE_BASE
import ikigaiworks.imgurapi.SECRET_IMGUR_APP_ID

class WebViewUtils {
    companion object {
        /**
         * Generate the full URL to the Imgur OAuth 2 login page.
         *
         * This URL includes our app id and the response type we want (token).
         * @return Full Uri to the Imgur OAuth 2 login page.
         */
        fun generateLoginUrl(): Uri {
            val loginUriBuilder = Uri.parse(OAUTH_AUTHORIZE_BASE).buildUpon()
            loginUriBuilder.appendQueryParameter("client_id", ARG_IMGUR_APP_ID)
            loginUriBuilder.appendQueryParameter("response_type", "token")

            return loginUriBuilder.build()
        }
    }
}