package data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VacancyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(newEntity: VacancyEntity)

    @Query("SELECT * FROM VacancyEntity WHERE isFavorite = 1")
    suspend fun getFavourites(): List<VacancyEntity>

    @Query("SELECT * FROM VacancyEntity WHERE isFavorite = 1")
    fun getFavouritesFlow(): Flow<List<VacancyEntity>>

    @Query("SELECT * FROM VacancyEntity WHERE id LIKE :id AND isFavorite = 1 LIMIT 1")
    suspend fun getFavouriteById(id: String): VacancyEntity?

}