package com.eugene.androidmaterialdesign

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.animation.LinearInterpolator
import com.eugene.androidmaterialdesign.databinding.ActivitySplashBinding

private const val ROTATION_BY_Y = 300f
private const val DURATION_ANIMATION = 3000L

class SplashActivity : AppCompatActivity(), Animator.AnimatorListener {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.splashImageView.animate().rotationYBy(ROTATION_BY_Y)
            .setInterpolator(LinearInterpolator())
            .setDuration(DURATION_ANIMATION)
            .setListener(this)
    }

    override fun onAnimationEnd(p0: Animator?) {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }

    override fun onAnimationStart(p0: Animator?) {}
    override fun onAnimationCancel(p0: Animator?) {}
    override fun onAnimationRepeat(p0: Animator?) {}
}