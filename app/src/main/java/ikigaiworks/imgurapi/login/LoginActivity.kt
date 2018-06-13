package ikigaiworks.imgurapi.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ikigaiworks.imgurapi.R
import ikigaiworks.imgurapi.main.MainFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val newFragment = LoginWebViewFragment()

        val transaction = supportFragmentManager.beginTransaction()

        // Replace whatever is in the fragment_container view with this fragment,
        // and DO NOT add the transaction to the back stack since we don't want the user to
        // be able to navigate back
        transaction.replace(R.id.fragment_container, newFragment, "loginWebViewFragment")

        // Commit the transaction
        transaction.commit()
    }
}
