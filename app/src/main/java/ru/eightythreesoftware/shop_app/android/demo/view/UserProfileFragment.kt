package ru.eightythreesoftware.shop_app.android.demo.view

import android.annotation.SuppressLint
import android.app.ProgressDialog.show
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.eightythreesoftware.shop_app.android.demo.R
import ru.eightythreesoftware.shop_app.android.demo.model.User
import ru.eightythreesoftware.shop_app.android.demo.view.recycler_views.UserOrdersRecyclerViewAdapter
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.OrdersViewModel
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.UserViewModel
import java.util.*

class UserProfileFragment : Fragment() {

    private val userProfileViewModel: UserViewModel by activityViewModels()
    private val ordersViewModel: OrdersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.user_profile_fragment, container, false)
        setParams(view)
        return view
    }

    @SuppressLint("SetTextI18n")
    private fun setParams(view: View){
        try {
            val userName: TextView = view.findViewById(R.id.user_profile_fragment_user_name)
            val userEmail: TextView = view.findViewById(R.id.user_profile_fragment_user_email)
            val userAddress: TextView = view.findViewById(R.id.user_profile_fragment_user_address)
            val userPhoneNumber: TextView = view.findViewById(R.id.user_profile_fragment_user_phone_number)
            val userImage: ImageView = view.findViewById(R.id.user_profile_fragment_user_icon)
            val userBirthday: TextView = view.findViewById(R.id.user_profile_fragment_user_birthday)
            val userKYCVerificationIcon: ImageView = view.findViewById(R.id.user_profile_fragment_user_kyc_verification_icon)
            val changeProfileButton: ImageView = view.findViewById(R.id.user_profile_fragment_change_profile_button)
            val userOrdersRecyclerView: RecyclerView = view.findViewById(R.id.user_profile_fragment_recycler_view)
            userProfileViewModel.user.observe(viewLifecycleOwner){ user ->
                userName.text = "" +
                        "${user.firstName
                            .uppercase(Locale.getDefault())} ${user.lastName
                            .uppercase(
                        Locale.getDefault())}"
                userEmail.text = user.email
                userPhoneNumber.text = user.phone
                userBirthday.text = user.dateOfBirthday
                when(user.kyc){
                    true -> userKYCVerificationIcon.setImageResource(R.drawable.kyc_verified_icon)
                    false -> userKYCVerificationIcon.setImageResource(R.drawable.kyc_not_verified_icon)
                }
                userAddress.text = "${user.userAddress.city}, ${user.userAddress.street_address}"
                Glide.with(userImage)
                    .load(user.image)
                    .placeholder(R.drawable.loading_image)
                    .error(R.drawable.no_image_error)
                    .into(userImage)
                ordersViewModel.loadUserOrders(user.id)
                ordersViewModel.orders.observe(viewLifecycleOwner){ orders ->
                    userOrdersRecyclerView.adapter = UserOrdersRecyclerViewAdapter(orders)
                    userOrdersRecyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL,false)
                }
                userKYCVerificationIcon.setOnClickListener {
                    processKYC(user)
                }
                changeProfileButton.setOnClickListener {
                    changeProfile()
                }
            }
        }catch (throwable: Throwable){
            Toast.makeText(
                this.context,
                "Не удалось открыть профиль пользователя",
                Toast.LENGTH_LONG
            ).show()
                .also {
                Log.d("MAIN_DEBUG","FAIL: failed to set user profile fragment params ERROR: ${throwable.message}")
            }
        }
    }

    private fun processKYC(user: User){
        if(user.kyc){
            Toast.makeText(
                this.context,
                "Подтверждение больше не требуется, можете совершать покупки!",
                Toast.LENGTH_LONG
            ).show()
        }else{
            TODO()
        }
    }

    private fun changeProfile(){
        UserLoginFragment
            .newInstance()
            .show(
                parentFragmentManager,
                "login_dialog_fragment"
            )
    }

}