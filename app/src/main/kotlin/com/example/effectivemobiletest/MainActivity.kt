package com.example.effectivemobiletest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.auth.login.LoginFragment
import com.example.auth.pin.PinFragment
import com.example.effectivemobiletest.databinding.ActivityMainBinding
import domain.Vacancy
import org.koin.androidx.viewmodel.ext.android.viewModel
import utlil.EMAIL_KEY
import utlil.Navigation
import utlil.VACANCY_KEY

class MainActivity : AppCompatActivity(), Navigation {

    private lateinit var binding: ActivityMainBinding
    private val vm: MainVM by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.authContainer, LoginFragment())
            .commit()

        setupBottomNav()
        observeVM()
    }

    override fun navigateToPin(email: String) {
        supportFragmentManager.beginTransaction()
            .add(R.id.authContainer, PinFragment().apply {
                arguments = bundleOf(EMAIL_KEY to email)
            }).commit()
    }

    override fun navigateToWork(vacancy: Vacancy) {
        findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.workFragment, args = bundleOf(VACANCY_KEY to vacancy))
    }

    override fun closeAuth() {
        binding.authContainer.isVisible = false
    }

    override fun navigateBack() {
        findNavController(R.id.nav_host_fragment_activity_main).popBackStack()
    }

    private fun observeVM() {
        vm.favouriteCount.observe(this) { count ->
            binding.navView.getOrCreateBadge(R.id.navigation_favourite).apply {
                backgroundColor = ContextCompat.getColor(this@MainActivity, com.example.base.R.color.red)
                badgeTextColor = ContextCompat.getColor(this@MainActivity, com.example.base.R.color.white)
                isVisible = count > 0
                number = count
            }
        }
    }

    private fun setupBottomNav() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)
    }
}
