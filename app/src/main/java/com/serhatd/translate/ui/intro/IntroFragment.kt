package com.serhatd.translate.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.serhatd.translate.R
import com.serhatd.translate.databinding.FragmentIntroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroFragment : Fragment() {
    private lateinit var binding: FragmentIntroBinding
    private val viewModel: IntroViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro, container, false)
        binding.viewModel = viewModel

        viewModel.introSkippedObserver.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().popBackStack()
            }
        }

        return binding.root
    }
}