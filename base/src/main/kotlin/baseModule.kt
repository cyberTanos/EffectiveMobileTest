import utlil.JsonReader
import data.RepositoryImpl
import domain.Repository
import org.koin.dsl.module
import utlil.ResourceProvider

val baseModule = module {
    single { ResourceProvider(get()) }
    single { JsonReader(get()) }
    single<Repository> { RepositoryImpl(get(), get()) }
}