package com.android.example.icafe.history


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.android.example.icafe.R
import com.android.example.icafe.databinding.FragmentHistoryBinding

/**
 * A simple [Fragment] subclass.
 */
class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val binding = DataBindingUtil.inflate<FragmentHistoryBinding>(inflater,
           R.layout.fragment_history, container, false)
        return binding.root
    }


}
