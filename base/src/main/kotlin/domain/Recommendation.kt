package domain

import androidx.annotation.DrawableRes

data class Recommendation(
    val id: String,
    val title: String,
    val url: String,
    @DrawableRes val img: Int,
    val buttonText: String
) : RecyclerItem
