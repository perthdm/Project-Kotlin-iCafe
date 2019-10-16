package com.android.example.icafe


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.android.example.icafe.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)

            binding.loginButton.setOnClickListener{thisView ->
//                if(usernameInput.text.toString() == "admin" && passwordInput.text.toString() == "12345"){
                    thisView.findNavController().navigate(R.id.action_loginFragment_to_menuFragment)
//                }else{

//                }
            }


        return binding.root
    }


}
