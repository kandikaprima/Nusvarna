package com.dicoding.picodiploma.nusvarna.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.nusvarna.repository.Repository
import com.dicoding.picodiploma.nusvarna.ui.auth.LoginViewModel
import com.dicoding.picodiploma.nusvarna.ui.auth.RegisterViewModel
import com.dicoding.picodiploma.nusvarna.ui.main.MainViewModel
import com.dicoding.picodiploma.nusvarna.ui.main.fragment.pameran.PameranViewModel
import com.dicoding.picodiploma.nusvarna.ui.profile.ProfileSettingViewModel
import com.dicoding.picodiploma.nusvarna.ui.profile.ProfileViewModel

class ViewModelFactory private constructor(
    private val authRepository: Repository
): ViewModelProvider.Factory{

    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =  instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Repository.getInstance())
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(authRepository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(authRepository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(authRepository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(authRepository) as T
            }
            modelClass.isAssignableFrom(PameranViewModel::class.java) -> {
                PameranViewModel(authRepository) as T
            }
            modelClass.isAssignableFrom(ProfileSettingViewModel::class.java) -> {
                ProfileSettingViewModel(authRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}