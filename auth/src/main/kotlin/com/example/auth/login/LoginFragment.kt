package com.example.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.login.R
import com.example.login.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import utlil.Navigation

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val vm: LoginVM by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        bindUI()
        observeVM()
        return binding.root
    }

    private fun bindUI() {
        binding.button.setOnClickListener {
            if (vm.canNavigate()) {
                (requireActivity() as Navigation).navigateToPin(binding.inpInpFiz.text.toString())
            }
        }

        binding.inpInpFiz.doAfterTextChanged {
            vm.checkEmail(it.toString())
        }

        binding.inpLayFiz.setEndIconOnClickListener {
            binding.inpInpFiz.background =AppCompatResources.getDrawable(requireContext(), com.example.base.R.drawable.background_edit)
            binding.inpInpFiz.setText("")
            vm.clearError()
        }
    }

    private fun observeVM() {
        vm.isButtonEnabled.observe(viewLifecycleOwner) {
            binding.button.isEnabled = it
        }
        vm.error.observe(viewLifecycleOwner) {
            binding.textError.text = it
            binding.inpInpFiz.background =
                if (it.isEmpty()) AppCompatResources.getDrawable(requireContext(), com.example.base.R.drawable.background_edit)
                else AppCompatResources.getDrawable(requireContext(), com.example.base.R.drawable.background_edit_error)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
