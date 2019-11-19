package com.android.example.icafe.history


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.android.example.icafe.R
import com.android.example.icafe.database.DataHistoryDatabase
import com.android.example.icafe.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {
    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHistoryBinding>(inflater,R.layout.fragment_history,container, false)

        //=====  Database =====//
        val application = requireNotNull(this.activity).application
        val dataSource = DataHistoryDatabase.getInstance(application).dataHistoryDatabaseDao
        val viewModelFactory = HistoryViewModelFactory(dataSource, application)
        //=====  Database =====//

        viewModel = ViewModelProviders.of(this,viewModelFactory).get(HistoryViewModel::class.java)
        val adapter = HistoryAdapter()
        binding.historyRecycle.adapter = adapter
        binding.historyViewModel = viewModel
        binding.lifecycleOwner = this

        binding.setLifecycleOwner(this)



        viewModel.score.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    ///================ Right Menu for share ================///
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_for_history, menu)

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