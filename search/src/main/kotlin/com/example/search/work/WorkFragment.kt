package com.example.search.work

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.search.R
import com.example.search.databinding.FragmentWorkBinding
import com.example.search.otclick.OtklickBottomSheet
import com.example.search.otclick.OtklickBottomSheet.Companion.ARGS
import com.example.search.otclick.OtklickBottomSheet.Companion.TAG
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import domain.Vacancy
import org.koin.androidx.viewmodel.ext.android.viewModel
import utlil.Navigation
import utlil.VACANCY_KEY

class WorkFragment : Fragment(R.layout.fragment_work) {

    private var _binding: FragmentWorkBinding? = null
    private val binding get() = _binding!!
    private val vm: WorkVM by viewModel()
    private var fav: Boolean = false

    private val adapter = ListDelegationAdapter(
        questionAdapterDelegate {
            showBottomSheet(it)
        }
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWorkBinding.inflate(inflater, container, false)
        bindUI()
        return binding.root
    }

    private fun bindUI() {
        val args = arguments?.getSerializable(VACANCY_KEY) as Vacancy
        binding.textProf.text = args.title
        binding.textSalary.text = args.salary
        binding.textExp.text = "Требуемый опыт: ${args.experience.text}"
        binding.textFullDay.text = args.schedules
        val peopleOtclickText = requireContext().resources.getQuantityString(
            com.example.base.R.plurals.plurals_vacancies,
            args.appliedNumber,
            args.appliedNumber
        )
        binding.textPeopleOtclick.text = "$peopleOtclickText человек уже откликнулось"
        binding.linearLayout.isVisible = args.appliedNumber > 0
        val peopleOtclickLook = requireContext().resources.getQuantityString(
            com.example.base.R.plurals.plurals_vacancies,
            args.lookingNumber,
            args.lookingNumber
        )
        binding.textPeopleLook.text = "${peopleOtclickLook} сейчас смотрят"
        binding.linearLayout2.isVisible = args.lookingNumber > 0
        binding.textView.text = args.company
        binding.address.text = "${args.address.town}, ${args.address.street}, ${args.address.house}"
        binding.textCompanyDescription.isVisible = args.description.isNotEmpty()
        binding.textCompanyDescription.text = args.description
        binding.textYourTask.text = args.responsibilities
        binding.questions.adapter = adapter
        adapter.items = args.questions

        binding.imgBack.setOnClickListener {
            (requireActivity() as Navigation).navigateBack()
        }

        binding.button.setOnClickListener {
            showBottomSheet("")
        }

        binding.imgFavourite.setImageDrawable(
            if (args.isFavorite) AppCompatResources.getDrawable(requireContext(), com.example.base.R.drawable.ic_favourite_fill)
            else AppCompatResources.getDrawable(requireContext(), com.example.base.R.drawable.ic_favourite)
        )

        fav = args.isFavorite

        binding.imgFavourite.setOnClickListener {
            if (fav) binding.imgFavourite.setImageDrawable(
                AppCompatResources.getDrawable(requireContext(), com.example.base.R.drawable.ic_favourite)
            ) else AppCompatResources.getDrawable(requireContext(), com.example.base.R.drawable.ic_favourite_fill)
            fav = !fav
            vm.addFavorite(args.id)
        }
    }

    private fun showBottomSheet(question: String = "") {
        OtklickBottomSheet().apply {
            arguments = bundleOf(ARGS to question)
        }.show(childFragmentManager, TAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}