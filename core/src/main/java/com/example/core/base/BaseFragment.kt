package com.example.core.base

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.core.extentions.bindView


abstract class BaseFragment<B : ViewBinding, VM : ViewModel> : Fragment() {

    abstract fun onFragmentReady()

    protected abstract val mViewModel: VM

    private var _binding: B? = null
    lateinit var binding: B
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = bindView()
        binding = _binding!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentReady()
    }



    override fun onDestroyView() {

        _binding = null
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
    }


}
