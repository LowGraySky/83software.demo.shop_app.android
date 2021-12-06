package ru.eightythreesoftware.shop_app.android.demo.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import ru.eightythreesoftware.shop_app.android.demo.R
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.UserViewModel

class UserLoginFragment : DialogFragment() {

    private val userViewModel: UserViewModel by activityViewModels()

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater
            .from(this.context)
            .inflate(R.layout.user_login_fragment, null)
        val emailField: EditText = view.findViewById(R.id.user_login_fragment_user_email)
        val passwordField: EditText = view.findViewById(R.id.user_login_fragment_user_password)
        return AlertDialog.Builder(this.context)
            .setView(view)
            .setPositiveButton("LOGIN") { _, _ ->
                if(emailField.text.isNullOrEmpty() && passwordField.text.isNullOrEmpty()){
                    Toast.makeText(
                        this.context,
                        "Оба поля должны быть заполнены",
                        Toast.LENGTH_LONG).show()
                }else{
                    userViewModel.changeProfile(
                        emailField.text.toString(),
                        passwordField.text.toString()
                    )
                    dialog?.cancel()
                    Toast.makeText(
                        this.context,
                        "Попытка авторизации...",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            .setNegativeButton("CANCEL") { _, _ ->
                dialog?.cancel()
            }
            .setView(R.layout.user_login_fragment)
            .create()
    }

    companion object{

        fun newInstance() = UserLoginFragment()
    }
}