package data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class VacancyEntity(
    @PrimaryKey val id: String,
    val isFavorite: Boolean
)
