package com.android.example.icafe.detail


import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.android.example.icafe.R
import com.android.example.icafe.databinding.FragmentDetailBinding
import java.time.LocalDateTime


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDetailBinding>(inflater,
           R.layout.fragment_detail, container, false)

        binding.manageSubmitButton.setOnClickListener{thisView ->

            thisView.findNavController().navigate(R.id.action_detailFragment_to_manageFragment)
            Log.i("Log","Submit Data")


            val timer = object: CountDownTimer(20000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    println(millisUntilFinished/1000)
                }

                override fun onFinish() {
                    Log.i("Log","Finish")
                }
            }
            timer.start()

        }

        return binding.root
    }


}
