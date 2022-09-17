package com.khlafawi.aniamtion

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.animation.LinearInterpolator
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var screenHeight = 0f
    private val DEFAULT_ANIMATION_DURATION = 2500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container.setOnClickListener {
            moveRocketOnly()
            moveRocketWithDoge()
            //rotateRocketOnly()
            //animationWithAnimatorListener()
            //moveDogeAndRocketWithRotatingDoge()
        }
    }

    override fun onResume() {
        super.onResume()

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenHeight = displayMetrics.heightPixels.toFloat()
    }

    private fun moveRocketOnly() {
        val valueAnimator = ValueAnimator.ofFloat(0f, -screenHeight)

        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            rocket.translationY = value
        }

        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = DEFAULT_ANIMATION_DURATION

        valueAnimator.start()
    }

    private fun moveRocketWithDoge() {
        val valueAnimator = ValueAnimator.ofFloat(0f, -screenHeight)

        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            rocket.translationY = value
            doge.translationY = value
        }

        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = DEFAULT_ANIMATION_DURATION

        valueAnimator.start()
    }

    private fun rotateRocketOnly() {
        val valueAnimator = ValueAnimator.ofFloat(0f, 360f)

        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            rocket.rotation = value
        }

        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = DEFAULT_ANIMATION_DURATION
        valueAnimator.start()
    }

    private fun animationWithAnimatorListener() {
        val animator = ValueAnimator.ofFloat(0f, -screenHeight)

        animator.addUpdateListener {
            val value = it.animatedValue as Float
            rocket.translationY = value
            doge.translationY = value
        }

        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                Toast.makeText(applicationContext, "Doge took off", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onAnimationEnd(animation: Animator) {
                Toast.makeText(applicationContext, "Doge is on the moon", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}
        })

        animator.duration = 5000L
        animator.start()
    }

    private fun moveDogeAndRocketWithRotatingDoge() {
        val positionAnimator = ValueAnimator.ofFloat(0f, -screenHeight)
        positionAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            rocket.translationY = value
            doge.translationY = value
        }

        val rotationAnimator = ValueAnimator.ofFloat(0f, 360f)
        rotationAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            doge.rotation = value
        }

        val animatorSet = AnimatorSet()
        animatorSet.play(positionAnimator).with(rotationAnimator)
        animatorSet.duration = DEFAULT_ANIMATION_DURATION
        animatorSet.start()
    }
}