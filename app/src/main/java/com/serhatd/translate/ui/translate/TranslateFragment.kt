package com.serhatd.translate.ui.translate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.serhatd.translate.R
import com.serhatd.translate.databinding.FragmentTranslateBinding

class TranslateFragment : Fragment() {
    private lateinit var binding: FragmentTranslateBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_translate, container, false)

        return binding.root
    }
}