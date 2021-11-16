package ru.eightythreesoftware.shop_app.android.demo.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.eightythreesoftware.shop_app.android.demo.R
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.UserProfileViewModel

class UserProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.user_profile_fragment, container, false)
    }

    companion object {
        fun newInstance() = UserProfileFragment()
    }
}