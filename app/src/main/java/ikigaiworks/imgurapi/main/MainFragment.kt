package ikigaiworks.imgurapi.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ikigaiworks.imgurapi.R
import kotlinx.android.synthetic.main.fragment_imgur_login.*


class MainFragment : Fragment() , View.OnClickListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_imgur_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      buttonLogin.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        Log.v("login", "doLogin")
        (activity as MainActivity).doLoginWebView();
    }


}