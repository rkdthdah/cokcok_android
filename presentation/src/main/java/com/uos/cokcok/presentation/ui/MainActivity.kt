package com.uos.cokcok.presentation.ui

import android.animation.ObjectAnimator
import android.os.Build
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.uos.cokcok.presentation.R
import com.uos.cokcok.presentation.ui.base.BaseActivity
import com.uos.cokcok.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {

    override fun preload() {
        installSplashScreen()
    }

    override fun init() {
        setSplashScreen()
        // init tool bar
        initToolBar()
        // init bottom nav
        // bottom nav를 벗어나는 경우 bottom nav와 main tool bar를 숨김
        hideBar(initBottomNavigation())
    }

    private fun setSplashScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                //checkLogin()

                ObjectAnimator.ofFloat(splashScreenView, View.ALPHA, 1f, 0f).run {
                    interpolator = AnticipateInterpolator()
                    duration = 200L
                    doOnEnd { splashScreenView.remove() }
                    start()
                }
            }
        }
    }

    private fun checkLogin() {
        //todo 제한시간도 설정해야 함. 주구장창 스플래시만 보고있을 순 없으니.
    }

    private fun initToolBar() {
        binding.toolBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.notification -> {
                    when (binding.bottomNavigation.selectedItemId) {
                        R.id.home_fragment -> {
                            binding.navigationHost.findNavController().navigate(R.id.action_home_to_notification)
                        }
                        R.id.matching_fragment -> {
                            binding.navigationHost.findNavController().navigate(R.id.action_matching_to_notification)
                        }
                        R.id.club_fragment -> {
                            binding.navigationHost.findNavController().navigate(R.id.action_club_to_notification)
                        }
                        R.id.community_fragment -> {
                            binding.navigationHost.findNavController().navigate(R.id.action_community_to_notification)
                        }
                    }
                    true
                }
                R.id.my_page -> {
                    when (binding.bottomNavigation.selectedItemId) {
                        R.id.home_fragment -> {
                            binding.navigationHost.findNavController().navigate(R.id.action_home_to_my_page)
                        }
                        R.id.matching_fragment -> {
                            binding.navigationHost.findNavController().navigate(R.id.action_matching_to_my_page)
                        }
                        R.id.club_fragment -> {
                            binding.navigationHost.findNavController().navigate(R.id.action_club_to_my_page)
                        }
                        R.id.community_fragment -> {
                            binding.navigationHost.findNavController().navigate(R.id.action_community_to_my_page)
                        }
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun initBottomNavigation(): NavController {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navigation_host) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.bottomNavigation.setupWithNavController(navController)

        return navController
    }

    private fun hideBar(navController: NavController) {
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.home_fragment,
            R.id.matching_fragment,
            R.id.community_fragment,
            R.id.club_fragment
        ))

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation.isVisible = appBarConfiguration.topLevelDestinations.contains(destination.id)
            binding.toolBar.isVisible = appBarConfiguration.topLevelDestinations.contains(destination.id)
        }
    }
}
