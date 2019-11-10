package com.android.example.icafe.about


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.android.example.icafe.R
import com.android.example.icafe.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentAboutBinding>(inflater,R.layout.fragment_about, container, false)

        setHasOptionsMenu(true)
        return binding.root
    }


    ///================ Right Menu for share ================///
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_for_about, menu)

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
