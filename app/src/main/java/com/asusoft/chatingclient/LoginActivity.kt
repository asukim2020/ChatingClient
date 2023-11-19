package com.asusoft.chatingclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.asusoft.chatingclient.databinding.ActivityLoginBinding
import com.asusoft.chatingclient.viewmodel.LoginViewModel
import com.asusoft.chatingclient.viewmodel.ViewModelFactory

class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModels<LoginViewModel> { ViewModelFactory }
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater).apply {
            viewmodel = viewModel
            lifecycleOwner = this@LoginActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }


}