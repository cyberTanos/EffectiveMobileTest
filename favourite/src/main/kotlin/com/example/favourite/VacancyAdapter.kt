package com.example.favourite

import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import com.example.base.databinding.ItemSearchWorkBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import domain.RecyclerItem
import domain.Vacancy

fun vacancyAdapterDelegate(
    onClick: (Vacancy) -> Unit,
    onFavoriteClick: (String) -> Unit
) = adapterDelegateViewBinding<Vacancy, RecyclerItem, ItemSearchWorkBinding>({ layoutInflater, root ->
    ItemSearchWorkBinding.inflate(layoutInflater, root, false)
}) {
    binding.root.setOnClickListener {
        onClick.invoke(item)
    }

    bind {
        binding.textNow.isVisible = item.lookingNumber > 0
        val people: String =
            context.resources.getQuantityString(com.example.base.R.plurals.plurals_vacancies, item.lookingNumber, item.lookingNumber)
        binding.textNow.text = "Сейчас просматривает $people"

        binding.imgFavourite.setImageDrawable(
            if (item.isFavorite) AppCompatResources.getDrawable(context, com.example.base.R.drawable.ic_favourite_fill)
            else AppCompatResources.getDrawable(context, com.example.base.R.drawable.ic_favourite)
        )
        binding.imgFavourite.setOnClickListener {
            onFavoriteClick.invoke(item.id)
        }
        binding.textProf.text = item.title
        binding.textCity.text = item.address.town
        binding.textExp.text = item.experience.previewText
        binding.textDate.text = item.publishedDate
    }
}
