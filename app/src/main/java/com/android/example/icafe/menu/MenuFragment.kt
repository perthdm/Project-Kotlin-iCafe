package com.android.example.icafe.menu


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.android.example.icafe.R
import com.android.example.icafe.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private lateinit var viewModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentMenuBinding>(inflater, R.layout.fragment_menu, container, false)

        Log.i("MenuFragment", "Called --> ViewModelProviders.of MenuViewModel")
        viewModel = ViewModelProviders.of(this).get(MenuViewModel::class.java)

        binding.menuViewModel = viewModel
        binding.lifecycleOwner = this


        //Click --> Manager Page
        viewModel.eventClickManager.observe(this, Observer<Boolean>{  boolean ->
            if (boolean) {
//                this.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToManageFragment("Menu"))
                this.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToManageFragment())
            }
        })

        //Click --> History Page
        viewModel.eventClickHistory.observe(this, Observer<Boolean>{  boolean ->
            if (boolean) {
                this.findNavController().navigate(R.id.action_menuFragment_to_historyFragment)
            }
        })

        //Click --> About Page
        viewModel.eventClickAbout.observe(this, Observer<Boolean>{  boolean ->
            if (boolean) {
                this.findNavController().navigate(R.id.action_menuFragment_to_aboutFragment)
            }
        })

        return binding.root
    }
}
