package ikigaiworks.imgurapi.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ikigaiworks.imgurapi.R
import ikigaiworks.imgurapi.login.LoginActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val newFragment = MainFragment()

        val transaction = supportFragmentManager.beginTransaction()

        // Replace whatever is in the fragment_container view with this fragment,
        // and DO NOT add the transaction to the back stack since we don't want the user to
        // be able to navigate back
        transaction.replace(R.id.fragment_container, newFragment, "MainFragment")

        // Commit the transaction
        transaction.commit()
    }

    fun doLoginWebView(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}