package com.android.example.icafe.detail


import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.android.example.icafe.R
import com.android.example.icafe.database.DataHistoryDatabase
import com.android.example.icafe.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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
        viewModel.eventInit.observe(this, Observer<Boolean> { boolean ->
            if(boolean){
                binding.comNumberValue.setText("${args.safeComSelected}")
            }
        })
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
                this.findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToManageFragment(dataID))
//                this.findNavController().navigate(R.id.action_detailFragment_to_manageFragment)
                Log.i("DetailFragment","Submit Data")
            }
        })
        ///================ STEP-3 Call --> Clicked Submit  ================///




        return binding.root
    }


}
