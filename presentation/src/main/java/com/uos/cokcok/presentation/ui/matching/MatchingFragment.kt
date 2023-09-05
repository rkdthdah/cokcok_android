package com.uos.cokcok.presentation.ui.matching

import androidx.fragment.app.viewModels
import com.uos.cokcok.presentation.R
import com.uos.cokcok.presentation.ui.base.BaseFragment
import com.uos.cokcok.presentation.databinding.FragmentMatchingBinding
import com.uos.cokcok.presentation.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchingFragment : BaseFragment<FragmentMatchingBinding>(
    R.layout.fragment_matching
) {
    private val viewModel: MatchingViewModel by viewModels()

    //onViewCreated() override 에서 livedata observing, view 초기값, adapter(recyclerview, viewpager2) 설정

    @Override
    override fun initView() {
        binding.vm = viewModel
        val activityBinding = (requireActivity() as MainActivity).binding
    }
}