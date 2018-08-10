package ikigaiworks.imgurapi.login

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ikigaiworks.imgurapi.R
import ikigaiworks.imgurapi.api.model.ImgurAccessToken
import ikigaiworks.imgurapi.api.model.ImgurImage
import kotlinx.android.synthetic.main.fragment_imgur_webview.*


class LoginWebViewFragment : Fragment() , LoginCallback{

    var loginWebview: LoginWebView? = null

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment LoginWebViewFragment.
     */
    companion object {
        fun newInstance(): LoginWebViewFragment {
            val fragment = LoginWebViewFragment()
            val image : ImgurImage = ImgurImage()

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_imgur_webview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // make sure that JavaScript is enabled
        val webSettings = imgurWebView.getSettings()
        webSettings.setJavaScriptEnabled(true)

        loginWebview = LoginWebView(this,context)
        // attach our web client that will monitor the WebView's URL looking for our app redirect URL
        imgurWebView.setWebViewClient(loginWebview)

        // load our Imgur OAuth login url
        imgurWebView.loadUrl(WebViewUtils.generateLoginUrl().toString())
    }

    override fun onResume() {
        super.onResume()
        activity?.invalidateOptionsMenu()
    }

    override fun onDetach() {
        super.onDetach()
        clearHistoryAndCache()
        imgurWebView.destroy()
    }

    /**
     * Clears the WebView's Cache, History, and Cookies
     */
    fun clearHistoryAndCache() {
        if (imgurWebView != null) {
            imgurWebView.clearCache(true)
            imgurWebView.clearHistory()
        }
    }

    override fun OnImgurTokenReceived(accessToken: ImgurAccessToken) {
        clearHistoryAndCache()
        Toast.makeText(context,accessToken.accessToken,Toast.LENGTH_LONG).show()
    }


}