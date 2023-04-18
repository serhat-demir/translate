package com.serhatd.translate.ui.translate

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.serhatd.translate.R
import com.serhatd.translate.databinding.FragmentTranslateBinding
import com.serhatd.translate.ui.helper.ConnectionManager
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.exitProcess

@AndroidEntryPoint
class TranslateFragment : Fragment() {
    private lateinit var binding: FragmentTranslateBinding
    private val viewModel: TranslateViewModel by viewModels()
    private lateinit var clipboard: ClipboardManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_translate, container, false)
        binding.fragment = this
        binding.viewModel = viewModel

        if (!ConnectionManager.checkInternetConnection(requireContext())) return binding.root
        clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        initObservers()
        initListeners()
        return binding.root
    }

    fun copyText(text: String) {
        val clipData = ClipData.newPlainText("", text)
        clipboard.setPrimaryClip(clipData)

        Toast.makeText(requireContext(), getString(R.string.msg_text_copied), Toast.LENGTH_SHORT).show()
    }

    fun pasteText(txt: EditText) {
        txt.setText(clipboard.primaryClip?.getItemAt(0)?.text)
    }

    private fun initListeners() {
        binding.spSrcLang.setOnSpinnerItemSelectedListener<String> { _, _, newIndex, _ ->
            viewModel.languages.value?.let {
                val name: String = viewModel.languages.value!![newIndex].name
                binding.spSrcLang.text = if (name.length > 7) name.substring(0, 3) + getString(R.string.three_dots) else name
            }
        }

        binding.spTarLang.setOnSpinnerItemSelectedListener<String> { _, _, newIndex, _ ->
            viewModel.languages.value?.let {
                val name: String = viewModel.languages.value!![newIndex].name
                binding.spTarLang.text = if (name.length > 7) name.substring(0, 3) + getString(R.string.three_dots) else name
            }
        }
    }

    private fun initObservers() {
        viewModel.introObserver.observe(viewLifecycleOwner) {
            it?.let {
                if (it)
                    viewModel.getLanguages()
                else
                    findNavController().navigate(R.id.translateToIntro)
            }
        }

        viewModel.languages.observe(viewLifecycleOwner) {
            it?.let {
                binding.spSrcLang.setItems(it.map {it1 -> it1.name})
                binding.spTarLang.setItems(it.map {it1 -> it1.name})

                binding.spSrcLang.selectItemByIndex(0)
                binding.spTarLang.selectItemByIndex(0)
            }
        }

        viewModel.translatedText.observe(viewLifecycleOwner) {
            it?.let {
                binding.txtTarLang.setText(it)
            }
        }

        viewModel.errorObserver.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle(getString(R.string.alert_dialog_title_app_unavailable))
                    builder.setMessage(R.string.alert_dialog_message_app_unavailable)
                    builder.setPositiveButton(R.string.alert_dialog_button_ok) { dialog: DialogInterface, _: Int ->
                        dialog.dismiss()
                        exitProcess(0)
                    }
                    builder.setCancelable(false)
                    builder.show()
                }
            }
        }

        viewModel.isIntroSkipped()
    }
}