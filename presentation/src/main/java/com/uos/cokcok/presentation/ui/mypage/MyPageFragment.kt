package com.uos.cokcok.presentation.ui.mypage

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.uos.cokcok.presentation.R
import com.uos.cokcok.presentation.ui.base.BaseFragment
import com.uos.cokcok.presentation.databinding.FragmentMyPageBinding
import com.uos.cokcok.presentation.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(
    R.layout.fragment_my_page
) {
    private val viewModel: MyPageViewModel by viewModels()

    @Override
    override fun initView() {
        binding.vm = viewModel
        initToolBar()
        binding.loginButton.setOnClickListener {
            this.findNavController().navigate(R.id.action_my_page_to_login)
        }
    }

    private fun initToolBar() {
        val activityBinding = (requireActivity() as MainActivity).binding

        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
    }
}