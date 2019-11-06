package com.android.example.icafe.menu


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.android.example.icafe.R
import com.android.example.icafe.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentMenuBinding>(inflater, R.layout.fragment_menu, container, false)


        binding.menuManageButton.setOnClickListener{thisView ->
            thisView.findNavController().navigate(R.id.action_menuFragment_to_manageFragment)
        }

        binding.menuHistoryButton.setOnClickListener{thisView ->
            thisView.findNavController().navigate(R.id.action_menuFragment_to_historyFragment)
        }

        binding.menuAboutButton.setOnClickListener{thisView ->
            thisView.findNavController().navigate(R.id.action_menuFragment_to_aboutFragment)
        }


        return binding.root
    }





}
