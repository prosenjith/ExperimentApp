package com.example.experimentapp.workmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.experimentapp.databinding.ActivityWorkerBinding
import java.util.concurrent.TimeUnit

class WorkerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorkerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val oneTimeWorkRequest = OneTimeWorkRequestBuilder<MyWorker>().build()
        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest)

        val periodicWorkRequest = PeriodicWorkRequestBuilder<MyWorker>(1, TimeUnit.MINUTES).build()
//        WorkManager.getInstance(this).enqueue(periodicWorkRequest)
    }
}