package com.example.auth.pin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.login.R
import com.example.login.databinding.FragmentPinBinding
import utlil.EMAIL_KEY
import utlil.Navigation
import utlil.hideKeyboard
import utlil.openKeyboard

class PinFragment : Fragment(R.layout.fragment_pin) {

    private var _binding: FragmentPinBinding? = null
    private val binding get() = _binding!!
    private val vm: PinVM by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPinBinding.inflate(inflater, container, false)
        bindUI()
        observeVM()
        return binding.root
    }

    private fun bindUI() {
        binding.pin.openKeyboard()
        binding.pin.doAfterTextChanged {
            vm.changePin(it.toString())
        }
        binding.title.text = "${binding.title.text} ${arguments?.getString(EMAIL_KEY)}"
        binding.button.setOnClickListener {
            requireActivity().hideKeyboard()
            (requireActivity() as Navigation).closeAuth()
        }
    }

    private fun observeVM() {
        vm.isButtonEnabled.observe(viewLifecycleOwner) {
            binding.button.isEnabled = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
