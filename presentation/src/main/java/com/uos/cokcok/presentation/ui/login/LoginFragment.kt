package com.uos.cokcok.presentation.ui.login

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.uos.cokcok.presentation.R
import com.uos.cokcok.presentation.ui.base.BaseFragment
import com.uos.cokcok.presentation.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    R.layout.fragment_login
) {
    private val viewModel: LoginViewModel by viewModels()

    @Override
    override fun initView() {
        binding.vm = viewModel

        initViewModelCallback()

        initButton()

    }

    private fun initButton() {
        binding.kakaoButton.setOnClickListener {
            viewModel.kakaoLogin()
        }
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            isLogin.observe(this@LoginFragment, Observer {
                if (isLogin.value == false) {
                    showToast(getString(R.string.toast_login_fail))
                } else {
                }
            })
        }
    }
}