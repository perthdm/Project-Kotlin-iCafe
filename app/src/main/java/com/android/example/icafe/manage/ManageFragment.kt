package com.android.example.icafe.manage


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.android.example.icafe.R
import com.android.example.icafe.database.DataHistoryDatabase
import com.android.example.icafe.databinding.FragmentManageBinding

class ManageFragment : Fragment() {
    private lateinit var viewModel: ManageViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentManageBinding>(inflater, R.layout.fragment_manage, container, false)
        val args = ManageFragmentArgs.fromBundle(arguments!!)

        //=====  Database =====//
        val application = requireNotNull(this.activity).application
        val dataSource = DataHistoryDatabase.getInstance(application).dataHistoryDatabaseDao
        val viewModelFactory = ManageViewModelFactory(dataSource,application)
        //=====  Database =====//


        viewModel = ViewModelProviders.of(this,viewModelFactory).get(ManageViewModel::class.java)
        binding.manageViewModel = viewModel
        binding.lifecycleOwner = this

        Log.i("ManageFragment", "Called --> ViewModelProviders.of ManageViewModel")
        Log.i("ManageFragment",args.dataID)

        viewModel.eventClickSubmit.observe(this, Observer<Boolean> { boolean ->
            if (boolean) {
                viewModel.safeArgs.observe(this, Observer<Int> { safeArgs ->
                    this.findNavController().navigate(ManageFragmentDirections.actionManageFragmentToDetailFragment(safeArgs))
                })
            }
        })

        viewModel.toastPleaseSelect.observe(this, Observer<Boolean> { boolean ->
            if (boolean) { Toast.makeText(activity, "Please select some button", Toast.LENGTH_LONG).show() }
        })

        viewModel.eventChangeColor.observe(this, Observer<Int> { button ->
//            if (button != 0 && button != null) {
//
//                binding.apply {
//                    manage1Button.setBackgroundColor(Color.parseColor("#FFFFFF"))
//                    manage2Button.setBackgroundColor(Color.parseColor("#FFFFFF"))
//                    manage3Button.setBackgroundColor(Color.parseColor("#FFFFFF"))
//                    manage4Button.setBackgroundColor(Color.parseColor("#FFFFFF"))
//                    manage5Button.setBackgroundColor(Color.parseColor("#FFFFFF"))
//                    manage6Button.setBackgroundColor(Color.parseColor("#FFFFFF"))
//                    when (button) {
//                        1 -> manage1Button.setBackgroundColor(Color.parseColor("#EBFF24"))
//                        2 -> manage2Button.setBackgroundColor(Color.parseColor("#EBFF24"))
//                        3 -> manage3Button.setBackgroundColor(Color.parseColor("#EBFF24"))
//                        4 -> manage4Button.setBackgroundColor(Color.parseColor("#EBFF24"))
//                        5 -> manage5Button.setBackgroundColor(Color.parseColor("#EBFF24"))
//                        6 -> manage6Button.setBackgroundColor(Color.parseColor("#EBFF24"))
//
//                    }
//                }
//            }


        })



        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_for_manager, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!, view!!.findNavController()) || super.onOptionsItemSelected(item)
    }




}
