package com.uos.cokcok.presentation.ui.home

import androidx.fragment.app.viewModels
import com.uos.cokcok.presentation.R
import com.uos.cokcok.presentation.databinding.FragmentHomeBinding
import com.uos.cokcok.presentation.ui.base.BaseFragment
import com.uos.cokcok.presentation.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    R.layout.fragment_home
) {
    private val viewModel: HomeViewModel by viewModels()

    //onViewCreated() override 에서 livedata observing, view 초기값, adapter(recyclerview, viewpager2) 설정

    // mainActivty의 상단바와 하단 네비게이션 바에 접근하기 위함


    @Override
    override fun initView() {
        binding.vm = viewModel
        val activityBinding = (requireActivity() as MainActivity).binding


    }
}