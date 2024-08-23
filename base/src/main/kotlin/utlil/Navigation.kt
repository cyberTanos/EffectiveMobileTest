package utlil

import domain.Vacancy

interface Navigation {
    fun navigateToPin(email: String)
    fun navigateToWork(vacancy: Vacancy)
    fun closeAuth()
    fun navigateBack()
}

const val EMAIL_KEY = "EMAIL_KEY"
const val VACANCY_KEY = "VACANCY_KEY"
