package com.uos.cokcok.presentation.ui.club

import androidx.fragment.app.viewModels
import com.uos.cokcok.presentation.R
import com.uos.cokcok.presentation.ui.base.BaseFragment
import com.uos.cokcok.presentation.databinding.FragmentClubBinding
import com.uos.cokcok.presentation.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClubFragment : BaseFragment<FragmentClubBinding>(
    R.layout.fragment_club
) {
    private val viewModel: ClubViewModel by viewModels()

    //onViewCreated() override 에서 livedata observing, view 초기값, adapter(recyclerview, viewpager2) 설정

    @Override
    override fun initView() {
        binding.vm = viewModel
        val activityBinding = (requireActivity() as MainActivity).binding
    }
}