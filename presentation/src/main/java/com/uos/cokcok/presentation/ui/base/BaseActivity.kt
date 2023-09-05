package com.uos.cokcok.presentation.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<B: ViewDataBinding>(
    @LayoutRes val layoutId: Int
): AppCompatActivity(), CoroutineScope {

    lateinit var binding: B
    lateinit var job: Job

    override val coroutineContext: CoroutineContext get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preload()

        binding = DataBindingUtil.setContentView(this@BaseActivity, layoutId)
        binding.lifecycleOwner = this@BaseActivity

        job = SupervisorJob()

        init()
    }

    abstract fun preload()

    abstract fun init()

    @Override
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}