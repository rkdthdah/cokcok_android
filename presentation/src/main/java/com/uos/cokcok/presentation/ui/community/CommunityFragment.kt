package com.uos.cokcok.presentation.ui.community

import androidx.fragment.app.viewModels
import com.uos.cokcok.presentation.R
import com.uos.cokcok.presentation.ui.base.BaseFragment
import com.uos.cokcok.presentation.databinding.FragmentCommunityBinding
import com.uos.cokcok.presentation.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunityFragment : BaseFragment<FragmentCommunityBinding>(
    R.layout.fragment_community
) {
    private val viewModel: CommunityViewModel by viewModels()

    //onViewCreated() override 에서 livedata observing, view 초기값, adapter(recyclerview, viewpager2) 설정

    @Override
    override fun initView() {
        binding.vm = viewModel
        val activityBinding = (requireActivity() as MainActivity).binding
    }
}