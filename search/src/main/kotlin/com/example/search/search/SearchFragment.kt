package com.example.search.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.search.databinding.FragmentSearchBinding
import com.example.search.search.adapter.buttonAdapterDelegate
import com.example.search.search.adapter.offerAdapterDelegate
import com.example.search.search.adapter.vacancyAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import utlil.Navigation

class SearchFragment : Fragment(com.example.search.R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val vm: SearchVM by viewModel()
    var isFullOpen = false

    private val adapterVacancies = ListDelegationAdapter(
        vacancyAdapterDelegate({
            (requireActivity() as Navigation).navigateToWork(it)
        }, {
            vm.addFavorite(it, isFullOpen)
        }),
        buttonAdapterDelegate {
            showFullVacancies()
            vm.getVacanciesFull()
            isFullOpen = true
        }
    )

    private val adapterRecommendations = ListDelegationAdapter(
        offerAdapterDelegate {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            startActivity(browserIntent)
        }
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        vm.getVacanciesShort()
        vm.getRecommendations()
        bindUI()
        observeVM()
        return binding.root
    }

    private fun bindUI() {
        binding.works.adapter = adapterVacancies
        binding.recomendations.adapter = adapterRecommendations
    }

    private fun observeVM() {
        vm.vacancies.observe(viewLifecycleOwner) {
            adapterVacancies.items = it
            adapterVacancies.notifyDataSetChanged()
            val vacanciesText = requireContext().resources.getQuantityString(
                com.example.base.R.plurals.plurals_vacancies_full, it.size, it.size
            )
            binding.textVacancies.text = vacanciesText
        }
        vm.recommendations.observe(viewLifecycleOwner) {
            adapterRecommendations.items = it
        }
    }

    private fun showFullVacancies() {
        binding.textVacancies.isVisible = true
        binding.textVacanciesFilter.isVisible = true
        binding.imgVacanciesFilter.isVisible = true
        binding.textForYou.isVisible = false
        binding.recomendations.isVisible = false
        binding.inpSerch.startIconDrawable = AppCompatResources.getDrawable(requireContext(), com.example.base.R.drawable.ic_back)
        binding.inpSerch.setStartIconOnClickListener {
            showShortVacancies()
        }
    }

    private fun showShortVacancies() {
        isFullOpen = false
        vm.getVacanciesShort()
        binding.textVacancies.isVisible = false
        binding.textVacanciesFilter.isVisible = false
        binding.imgVacanciesFilter.isVisible = false
        binding.textForYou.isVisible = true
        binding.recomendations.isVisible = true
        binding.inpSerch.startIconDrawable = AppCompatResources.getDrawable(requireContext(), com.example.base.R.drawable.ic_search)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
