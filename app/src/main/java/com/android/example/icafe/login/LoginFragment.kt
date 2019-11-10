package com.android.example.icafe.login


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.android.example.icafe.R
import com.android.example.icafe.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater,R.layout.fragment_login, container, false)

        Log.i("LoginFragment", "Called --> ViewModelProviders.of LoginViewModel")
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this

//        val args = LoginFragmentArgs.fromBundle(arguments!!)


        //Call --> Submit and sent username/password
        viewModel.eventCheckInput.observe(this, Observer<Boolean> { boolean ->
            if (boolean) {
                viewModel.checkInput(usernameInput.text.toString(), passwordInput.text.toString())
            }
        })

        //TOAST --> Toast input is empty
        viewModel.toastEmptyInput.observe(this, Observer<Boolean> { boolean ->
            if (boolean) {
                Toast.makeText(activity, "Input is empty", Toast.LENGTH_LONG).show()
                viewModel._toastEmptyInput.value = false
            }
        })


        //TOAST --> show username and password
        viewModel.toastShowMessage.observe(this, Observer<Boolean> { boolean ->
            if (boolean) {
                Toast.makeText(activity, "Username: admin // Password: 12345", Toast.LENGTH_LONG).show()
                viewModel._toastShowMessage.value = false
            }
        })

        //Call --> Success Login
        viewModel.eventSuccessLogin.observe(this, Observer<Boolean> { boolean ->
            if (boolean) {
                this.findNavController().navigate(R.id.action_loginFragment_to_menuFragment)
            }
        })

        return binding.root
    }
}