package data.local

import androidx.room.Room
import org.koin.dsl.module

val localModule = module {
    single {
        val db = Room.databaseBuilder(
            get(),
            VacancyDataBase::class.java, "database"
        ).build()
        db.getDao()
    }
}
