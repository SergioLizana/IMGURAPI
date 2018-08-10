package ikigaiworks.imgurapi.api.model

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import java.util.*

class ImgurAccessToken : Parcelable {
    /**
     * Get the access token/authorization token for use with the Imgur API
     * @return The token/authorization token for use with the Imgur API
     */
    val accessToken: String

    /**
     * Get the type of access token that we have.
     *
     * The Imgur API supports user-specific tokens using the "Bearer" type and
     * app-specific token with the "Client-ID" type.
     *
     * @return The type of access token that we have
     */
    val tokenType: String

    /**
     * Life of the token in seconds
     * @return Life of the token in seconds
     */
    val expiresIn: String

    /**
     * Get the token that can be used to refresh this access token.
     *
     * A refresh token is used to generate a new Access Token with the same permission/scope.
     * @return The refresh token
     */
    val refreshToken: String

    /**
     * Get the username for the user that logged in.
     * @return Username for the user that logged in
     */
    val accountUsername: String

    /**
     * Get the user ID of the user that logged in.
     * @return User ID of the user that logged in.
     */
    val accountId: String

    /**
     * The Date and time that this Access Token was created.
     * Used as a reference point for expiration.
     */
    private val created_at: Date

    /**
     * Get the approximate Date that this token will expire at.
     * @return Date that this token will expire at.
     */
    // if we don't have an expires time, default to 28 days
    // although the documentation mentions "1 month", I have been getting tokens that last 28 days
    val expires: Date
        get() {
            var uts = created_at.time

            if (!TextUtils.isEmpty(expiresIn)) {
                uts += java.lang.Long.parseLong(expiresIn) * 1000
            } else {
                uts += java.lang.Long.parseLong("2419200") * 1000
            }

            return Date(uts)
        }

    /**
     * Determine if the token is currently expired.
     * @return True if the token is expired, false otherwise.
     */
    val isExpired: Boolean?
        get() = isExpired(Date())

    /**
     * Construct an Access Token instance from all of its parts.
     *
     * @param access_token The access token/authorization token used with the API
     * @param token_type The type of access token this is Bearer or Client-ID
     * @param expires_in Number of seconds that this token is valid for
     * @param refresh_token A token that can be used to refresh this access token.
     * @param account_username The username for the user this represents.
     * @param account_id The id of the user this represents.
     */
    constructor(access_token: String, token_type: String, expires_in: String, refresh_token: String, account_username: String, account_id: String) {
        this.accessToken = access_token
        this.tokenType = token_type
        this.expiresIn = expires_in
        this.refreshToken = refresh_token
        this.accountUsername = account_username
        this.accountId = account_id

        this.created_at = Date()
    }

    /**
     * Determine if the token will be considered expired on the given Date.
     * @param now Date used to check against.
     * @return True if the token is considered expired on the given Date, false otherwise.
     */
    fun isExpired(now: Date): Boolean? {
        return !now.before(expires)
    }

    /**
     * Generate the value of the Authorization header to sent to the server in order to use this access token.
     * @return Value of the Authorization header to use.
     */
    fun toAuthorizationHeader(): String {
        return if (tokenType.toLowerCase() == "bearer") {
            "Bearer $accessToken"
        } else if (tokenType.toLowerCase() == "client-id") {
            "Client-ID $accessToken"
        } else {
            "$tokenType $accessToken"
        }
    }

    /**
     * Generate an appropriate String value for this instance.
     *
     * This currently the same as the toAuthorizationHeader() result.
     * @return String representation
     */
    override fun toString(): String {
        return toAuthorizationHeader()
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val that = o as ImgurAccessToken?

        return if (accessToken != that!!.accessToken) false else tokenType == that.tokenType
    }

    override fun hashCode(): Int {
        var result = accessToken.hashCode()
        result = 31 * result + tokenType.hashCode()
        return result
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ImgurAccessToken> = object : Parcelable.Creator<ImgurAccessToken> {
            override fun createFromParcel(source: Parcel): ImgurAccessToken = ImgurAccessToken(source)
            override fun newArray(size: Int): Array<ImgurAccessToken?> = arrayOfNulls(size)
        }

        /**
         * Helper function to generate a ImgurAccessToken instance from the URL fragment provided by the API.
         *
         * This comes from the App Redirect URL after requesting a "response_type" of "token"
         * from the Imgur OAuth API.
         *
         * @param fragment The "fragment" or "hash" from the app redirect URL that contains the access token information.
         * @return A ImgurAccessToken instance representing the given access token.
         */
        fun parseFromFragment(fragment: String): ImgurAccessToken {
            val fragmentQuery = Uri.parse("?$fragment")

            val access_token = fragmentQuery.getQueryParameter("access_token")
            val token_type = fragmentQuery.getQueryParameter("token_type")
            val expires_in = fragmentQuery.getQueryParameter("expires_in")
            val refresh_token = fragmentQuery.getQueryParameter("refresh_token")
            val account_username = fragmentQuery.getQueryParameter("account_username")
            val account_id = fragmentQuery.getQueryParameter("account_id")

            return ImgurAccessToken(access_token, token_type, expires_in, refresh_token, account_username, account_id)
        }
    }
}
