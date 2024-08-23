package data.remote

import com.example.base.R
import domain.Recommendation
import domain.Vacancy
import domain.Vacancy.Address
import domain.Vacancy.Experience
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun List<Response.VacancyResponse>?.toVacancy(): List<Vacancy> {
    return this?.map {
        Vacancy(
            id = it.id.orEmpty(),
            lookingNumber = it.lookingNumber ?: 0,
            title = it.title.orEmpty(),
            address = Address(
                town = it.addressResponse?.town.orEmpty(),
                street = it.addressResponse?.street.orEmpty(),
                house = it.addressResponse?.house.orEmpty()
            ),
            company = it.company.orEmpty(),
            experience = Experience(
                previewText = it.experienceResponse?.previewText.orEmpty(),
                text = it.experienceResponse?.text.orEmpty()
            ),
            publishedDate = parseDate(it.publishedDate.orEmpty()),
            isFavorite = it.isFavorite ?: true,
            salary = it.salaryResponse?.full.toString(),
            schedules = mapShedules(it.schedules),
            appliedNumber = it.appliedNumber ?: 0,
            description = it.description.orEmpty(),
            responsibilities = it.responsibilities.toString(),
            questions = it.questions?.map { it } ?: emptyList()
        )
    } ?: emptyList()
}

fun List<Response.OfferResponse>?.toRecommendation(): List<Recommendation> {
    return this?.map {
        Recommendation(
            id = it.id.orEmpty(),
            title = it.title.orEmpty(),
            url = it.link.orEmpty(),
            img = chooseIcon(it.id.orEmpty()),
            buttonText = it.button?.text.orEmpty()
        )
    } ?: emptyList()
}

private fun chooseIcon(id: String): Int {
    return when (id) {
        "near_vacancies" -> R.drawable.ic_person_blue
        "level_up_resume" -> R.drawable.ic_star
        "temporary_job" -> R.drawable.ic_list
        else -> 0
    }
}

private fun mapShedules(schedules: List<String?>?): String {
    if (schedules == null) return ""
    val builder = StringBuilder()
    schedules.forEachIndexed { index, s ->
        val text = if (index == 0) s?.capitalize() else s
        builder.append(text)
        if (index != schedules.size - 1) builder.append(", ")
    }
    return builder.toString()
}

private fun parseDate(date: String): String {
    val formattedDate = formatDate(date)
    return "Опубликовано $formattedDate"
}

fun formatDate(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMMM", Locale("ru"))

    return try {
        val date: Date = inputFormat.parse(dateString) ?: return ""
        outputFormat.format(date)
    } catch (e: Exception) {
        ""
    }
}
