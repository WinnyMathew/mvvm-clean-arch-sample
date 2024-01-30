package com.meals.mvvmcleanarchapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MVVMCleanArchApp @Inject constructor(): Application()