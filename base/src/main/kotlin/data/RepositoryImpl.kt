package data

import data.local.VacancyDao
import data.local.VacancyEntity
import data.remote.toRecommendation
import data.remote.toVacancy
import domain.Button
import domain.Repository
import kotlinx.coroutines.flow.Flow
import utlil.JsonReader
import domain.RecyclerItem

private const val JSON = "response.json"

class RepositoryImpl(
    private val reader: JsonReader,
    private val dao: VacancyDao
) : Repository {

    override suspend fun getVacanciesShort(): List<RecyclerItem> {
        val list = mutableListOf<RecyclerItem>()
        val vacanciesFavorite = reader.read(JSON).vacancies.toVacancy().map {
            it.copy(isFavorite = dao.getFavouriteById(it.id) != null)
        }
        val vacanciesShort = vacanciesFavorite.take(3)
        list.addAll(vacanciesShort)
        list.add(Button(vacanciesFavorite.size))
        return list
    }

    override suspend fun getVacanciesFull(): List<RecyclerItem> {
        val list = mutableListOf<RecyclerItem>()
        val vacaiesFavorite = reader.read(JSON).vacancies.toVacancy().map {
            it.copy(isFavorite = dao.getFavouriteById(it.id) != null)
        }
        list.addAll(vacaiesFavorite)
        return list
    }

    override suspend fun getRecommendations(): List<RecyclerItem> {
        return reader.read(JSON).offerResponses.toRecommendation()
    }

    override suspend fun insertFavorite(id: String) {
        val isFavorite = dao.getFavouriteById(id) != null
        dao.insertFavorite(VacancyEntity(id, !isFavorite))
    }

    override suspend fun getFavorites(): List<RecyclerItem> {
        val list = mutableListOf<RecyclerItem>()
        reader.read(JSON).vacancies.toVacancy().forEach { vaca ->
            if (dao.getFavourites().any { it.id == vaca.id }) list.add(vaca.copy(isFavorite = true))
        }
        return list
    }

    override suspend fun getFavoritesFlow(): Flow<List<VacancyEntity>> {
        return dao.getFavouritesFlow()
    }
}
