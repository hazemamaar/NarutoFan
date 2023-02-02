package com.example.home.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.core.base.BaseFragment
import com.example.home.R
import com.example.home.databinding.FragmentGetAllNarutoBinding
import com.example.home.ui.GetAllNarutoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetAllNarutoFragment : BaseFragment<FragmentGetAllNarutoBinding,GetAllNarutoViewModel>() {
    override fun onFragmentReady() {

    }

    override val mViewModel: GetAllNarutoViewModel by viewModels()


}