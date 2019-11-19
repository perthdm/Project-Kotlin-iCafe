package com.android.example.icafe.detail


import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.android.example.icafe.R
import com.android.example.icafe.database.DataHistoryDatabase
import com.android.example.icafe.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDetailBinding>(inflater, R.layout.fragment_detail,container,false)
        val args = DetailFragmentArgs.fromBundle(arguments!!)

        //=====  Database =====//
        val application = requireNotNull(this.activity).application
        val dataSource = DataHistoryDatabase.getInstance(application).dataHistoryDatabaseDao
        val viewModelFactory = DetailViewModelFactory(dataSource, application)
        //=====  Database =====//

        viewModel = ViewModelProviders.of(this,viewModelFactory).get(DetailViewModel::class.java)
        binding.detailViewModel = viewModel
        binding.lifecycleOwner = this



        ///================ STEP-1 Call --> Init ================///
        viewModel.constructor(args.safeComSelected)
        ///================ STEP-1 Call --> Init ================///



        ///================ STEP-2 Call --> eventGetInput ================///
        viewModel.eventGetInput.observe(this, Observer { boolean ->
            if (boolean){
                viewModel.checkInput(binding.detailNameInput.text.toString(),binding.detailAgeInput.text.toString(),args.safeComSelected)
            }
        })
        ///================ STEP-2 Call --> eventGetInput ================///



        ///================ TOAST --> Empty Input ================///
        viewModel.toastEmptyInput.observe(this, Observer { boolean ->
            if(boolean){
                Toast.makeText(activity, "Input is empty", Toast.LENGTH_LONG).show()
                viewModel._toastEmptyInput.value = false
            }
        })
        ///================ TOAST -->  Empty Input  ================///



        ///================ STEP-3 Call --> Clicked Submit  ================///
        viewModel.eventSubmitData.observe(this, Observer<Boolean> { boolean ->
            if(boolean){
                var dataID = viewModel.dataIdHitden.value.toString()
                Log.i("DetailFragment",dataID)
//                this.findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToManageFragment(dataID))
                this.findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToManageFragment())
            }
        })
        ///================ STEP-3 Call --> Clicked Submit  ================///



        setHasOptionsMenu(true)
        return binding.root
    }

    ///================ Right Menu for share ================///
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_for_history, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!, view!!.findNavController()) || super.onOptionsItemSelected(item)
    }
    ///================ Right Menu for share ================///


}
