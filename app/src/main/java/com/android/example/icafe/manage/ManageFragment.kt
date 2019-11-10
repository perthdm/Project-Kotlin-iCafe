package com.android.example.icafe.manage


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.android.example.icafe.R
import com.android.example.icafe.databinding.FragmentManageBinding

/**
 * A simple [Fragment] subclass.
 */
class ManageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentManageBinding>(inflater, R.layout.fragment_manage, container, false)
        var arr = arrayOf<Button>()

        binding.apply {
            arr = arrayOf(manage1Button, manage2Button, manage3Button, manage4Button,
                          manage5Button, manage6Button, manage7Button, manage8Button, manage9Button)

            manageSelectButton.setOnClickListener{thisView -> thisView.findNavController().navigate(
                R.id.action_manageFragment_to_detailFragment
            ) }

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

        setHasOptionsMenu(true)
        return binding.root
    }

   fun changeColor(selectButton:Button , arrayButton: Array<Button>){
       arrayButton.forEach { it.setBackgroundColor(Color.parseColor("#FFFFFF")) }
       selectButton.setBackgroundColor(Color.parseColor("#EBFF24"))
   }


    ///================ Right Menu for share ================///
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_for_manager, menu)

        if(null == getShareIntent().resolveActivity(activity!!.packageManager)){
            menu?.findItem(R.id.share)?.setVisible(false)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId){
            R.id.share -> shareSuccess()
        }

        return NavigationUI.onNavDestinationSelected(item!!, view!!.findNavController()) || super.onOptionsItemSelected(item)

    }
    ///================ Right Menu for share ================///


    private fun getShareIntent() : Intent {
        val shareIntent = Intent(Intent.ACTION_SEND)
        //val args = AboutFragmentArgs.fromBundle(arguments!!)

        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, "Shared")
        return shareIntent
    }

    private fun shareSuccess(){
        startActivity(getShareIntent())
    }

}
