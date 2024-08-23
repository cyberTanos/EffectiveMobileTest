package data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VacancyEntity::class], version = 1)
abstract class VacancyDataBase : RoomDatabase() {
    abstract fun getDao(): VacancyDao
}
