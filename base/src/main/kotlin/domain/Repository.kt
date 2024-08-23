package domain

import data.local.VacancyEntity
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getVacanciesShort(): List<RecyclerItem>
    suspend fun getVacanciesFull(): List<RecyclerItem>
    suspend fun getRecommendations(): List<RecyclerItem>
    suspend fun insertFavorite(id: String)
    suspend fun getFavorites(): List<RecyclerItem>
    suspend fun getFavoritesFlow(): Flow<List<VacancyEntity>>
}
