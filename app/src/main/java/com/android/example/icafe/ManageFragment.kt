package com.android.example.icafe


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
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
        var arr = arrayOf<Button>()

        binding.apply {
            arr = arrayOf(manage1Button, manage2Button, manage3Button, manage4Button,
                          manage5Button, manage6Button, manage7Button, manage8Button, manage9Button)

            manageSelectButton.setOnClickListener{thisView -> thisView.findNavController().navigate(R.id.action_manageFragment_to_detailFragment) }
            manage1Button.setOnClickListener{ thisView -> changeColor(binding.manage1Button, arr) }
            manage2Button.setOnClickListener{ thisView -> changeColor(binding.manage2Button, arr) }
            manage3Button.setOnClickListener{ thisView -> changeColor(binding.manage3Button, arr) }
            manage4Button.setOnClickListener{ thisView -> changeColor(binding.manage4Button, arr) }
            manage5Button.setOnClickListener{ thisView -> changeColor(binding.manage5Button, arr) }
            manage6Button.setOnClickListener{ thisView -> changeColor(binding.manage6Button, arr) }
            manage7Button.setOnClickListener{ thisView -> changeColor(binding.manage7Button, arr) }
            manage8Button.setOnClickListener{ thisView -> changeColor(binding.manage8Button, arr) }
            manage9Button.setOnClickListener{ thisView -> changeColor(binding.manage9Button, arr) }
        }

        return binding.root
    }

   fun changeColor(selectButton:Button , arrayButton: Array<Button>){
       arrayButton.forEach { it.setBackgroundColor(Color.parseColor("#FFFFFF")) }
       selectButton.setBackgroundColor(Color.parseColor("#EBFF24"))
   }


}
