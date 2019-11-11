package com.android.example.icafe.detail


import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.android.example.icafe.R
import com.android.example.icafe.databinding.FragmentDetailBinding
import java.time.LocalDateTime


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args = DetailFragmentArgs.fromBundle(arguments!!)
//        viewModel = ViewModelProviders.of(this,viewModelFactory).get(DetailViewModel::class.java)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        binding.detailViewModel = viewModel
        binding.lifecycleOwner = this



        ///================ Call --> Init ================///
        viewModel.eventInit.observe(this, Observer<Boolean> { boolean ->
            if(boolean){
                binding.comNumberValue.setText("${args.safeComSelected}")
            }
        })
        ///================ Call --> Init ================///



        ///================ Call --> eventGetInput ================///
        viewModel.eventGetInput.observe(this, Observer { boolean ->
            if (boolean){
                viewModel.checkInput(binding.detailNameInput.text.toString(),binding.detailTimeInput.text.toString())
            }
        })
        ///================ Call --> eventGetInput ================///



        ///================ TOAST --> Empty Input ================///
        viewModel.toastEmptyInput.observe(this, Observer { boolean ->
            if(boolean){
                Toast.makeText(activity, "Input is empty", Toast.LENGTH_LONG).show()
                viewModel._toastEmptyInput.value = false
            }
        })
        ///================ TOAST -->  Empty Input  ================///



        ///================ Call --> Clicked Submit  ================///
        viewModel.eventSubmitData.observe(this, Observer<Boolean> { boolean ->
            if(boolean){

//                viewModel.timeLiveData.observe(this, Observer { time ->
                    val timer = object: CountDownTimer(20000, 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                            Log.i("DetailFragment","${millisUntilFinished/1000}")
                        }
                        override fun onFinish() {
                            Log.i("DetailFragment","Finish")
                        }
                    }
                    timer.start()
//                })

                this.findNavController().navigate(R.id.action_detailFragment_to_manageFragment)
                Log.i("DetailFragment","Submit Data")


            }
        })
        ///================ Call --> Clicked Submit  ================///


        return binding.root
    }


}
