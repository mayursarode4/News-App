package com.mayursarode.newsapp

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.mayursarode.newsapp.utils.Constants
import com.mayursarode.newsapp.utils.TimeUtil
import com.mayursarode.newsapp.worker.NewsWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class NewsApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var hiltWorkerFactory: HiltWorkerFactory

    @Inject
    lateinit var workManager: WorkManager
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(hiltWorkerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()

        initWorkManager()
    }

    private fun initWorkManager() {

        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = PeriodicWorkRequest.Builder(
            NewsWorker::class.java,
            24,
            TimeUnit.HOURS
        )
            .setConstraints(constraint)
            .setInitialDelay(TimeUtil.getInitialDelay(), TimeUnit.MICROSECONDS)
            .addTag(Constants.UNIQUE_WORK_NAME)
            .build()

        workManager.enqueueUniquePeriodicWork(
            Constants.UNIQUE_WORK_NAME,
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            workRequest
        )
    }
}