package com.android.example.icafe


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import com.android.example.icafe.databinding.FragmentManageBinding

/**
 * A simple [Fragment] subclass.
 */
class ManageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val binding = DataBindingUtil.inflate<FragmentManageBinding>(inflater,R.layout.fragment_manage, container, false)
        binding.manageSelectButton.setOnClickListener{thisView ->
            thisView.findNavController().navigate(R.id.action_manageFragment_to_detailFragment)
        }

        return binding.root
    }


}
