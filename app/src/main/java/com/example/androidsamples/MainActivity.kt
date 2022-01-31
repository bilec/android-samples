package com.example.androidsamples

import android.content.Context
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import com.example.androidsamples.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var vibrator: Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vibrator = if (Build.VERSION.SDK_INT >= 31) {
            val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }


        binding.buttonVibrate.setOnClickListener {
            val vibrationTime = binding.seekBarVibrate.progress.toLong()
            val vibrationEffect = VibrationEffect.createOneShot(vibrationTime, VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.vibrate(vibrationEffect)
        }
    }
}