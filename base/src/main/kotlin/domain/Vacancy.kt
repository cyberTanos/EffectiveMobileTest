package domain

import kotlinx.serialization.Serializable

@Serializable
data class Vacancy(
    val id: String,
    val lookingNumber: Int,
    val title: String,
    val address: Address,
    val company: String,
    val experience: Experience,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: String,
    val schedules: String,
    val appliedNumber: Int,
    val description: String,
    val responsibilities: String,
    val questions: List<String>,
) : RecyclerItem, java.io.Serializable {

    @Serializable
    data class Experience(
        val previewText: String,
        val text: String
    )

    @Serializable
    data class Address(
        val town: String,
        val street: String,
        val house: String
    )
}
