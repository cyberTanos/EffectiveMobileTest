package com.example.search.work

import com.example.search.databinding.ItemQuestionBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun questionAdapterDelegate(
    onClick: (String) -> Unit
) = adapterDelegateViewBinding<String, String, ItemQuestionBinding>({ layoutInflater, root ->
    ItemQuestionBinding.inflate(layoutInflater, root, false)
}) {

    binding.root.setOnClickListener {
        onClick(item)
    }

    bind {
        binding.text.text = item
    }
}
