package com.example.search.search.adapter

import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import com.example.search.databinding.ItemSearchRecomendBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import domain.Recommendation
import domain.RecyclerItem

fun offerAdapterDelegate(
    onClick: (String) -> Unit
) = adapterDelegateViewBinding<Recommendation, RecyclerItem, ItemSearchRecomendBinding>({ layoutInflater, root ->
    ItemSearchRecomendBinding.inflate(layoutInflater, root, false)
}) {

    binding.root.setOnClickListener {
        onClick(item.url)
    }

    bind {
        if (item.img > 0) {
            binding.img.setImageDrawable(AppCompatResources.getDrawable(context, item.img))
        } else {
            binding.img.isVisible = false
        }

        binding.title.text = item.title

        binding.button.isVisible = item.buttonText.isBlank()
        binding.button.text = item.buttonText
    }
}
