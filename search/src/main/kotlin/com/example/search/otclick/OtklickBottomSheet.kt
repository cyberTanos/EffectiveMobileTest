package com.example.search.otclick

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.search.R
import com.example.search.databinding.BottomSheetOtclickBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OtklickBottomSheet : BottomSheetDialogFragment(R.layout.bottom_sheet_otclick) {

    private lateinit var binding: BottomSheetOtclickBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BottomSheetOtclickBinding.inflate(inflater, container, false)
        bindUI()
        return binding.root
    }

    private fun bindUI() {
        val args = arguments?.getString(ARGS).orEmpty()
        binding.edit.isVisible = args.isNotBlank()
        binding.edit.setText(args)
        binding.textAdd.isVisible = args.isBlank()
        binding.button.setOnClickListener {
            dismiss()
        }
        binding.textAdd.setOnClickListener {
            binding.textAdd.isVisible = false
            binding.edit.isVisible = true
        }
    }

    companion object {
        const val TAG = "CreateTaskBottomSheetTag"
        const val ARGS = "ARGS"
    }
}
