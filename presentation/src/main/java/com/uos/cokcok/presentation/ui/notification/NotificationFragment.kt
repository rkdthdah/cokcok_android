package com.uos.cokcok.presentation.ui.notification

import androidx.fragment.app.viewModels
import com.uos.cokcok.presentation.R
import com.uos.cokcok.presentation.ui.base.BaseFragment
import com.uos.cokcok.presentation.databinding.FragmentNotificationBinding
import com.uos.cokcok.presentation.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : BaseFragment<FragmentNotificationBinding>(
    R.layout.fragment_notification
) {
    private val viewModel: NotificationViewModel by viewModels()

    @Override
    override fun initView() {
        binding.vm = viewModel
        initToolBar()
    }

    private fun initToolBar() {
        // mainActivity 의 상단바, 하단 네비게이션 제거
        val activityBinding = (requireActivity() as MainActivity).binding

        // 좌측 뒤로가기 버튼
        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
    }
}