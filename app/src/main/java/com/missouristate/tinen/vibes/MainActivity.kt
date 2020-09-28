package com.missouristate.tinen.vibes

import android.animation.ArgbEvaluator
import android.animation.TimeAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    // initialize 8 MediaPlayers, one for each sound
    private lateinit var mp1: MediaPlayer
    private lateinit var mp2: MediaPlayer
    private lateinit var mp3: MediaPlayer
    private lateinit var mp4: MediaPlayer
    private lateinit var mp5: MediaPlayer
    private lateinit var mp6: MediaPlayer
    private lateinit var mp7: MediaPlayer
    private lateinit var mp8: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        initButtonsSliders()
    }

    override fun onResume() {
        super.onResume()
        // get colors from SharedPreferences
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val gradStart = sharedPrefs.getInt("gradient_start", 0)
        val gradCenter = sharedPrefs.getInt("gradient_middle", 0)
        val gradEnd = sharedPrefs.getInt("gradient_end", 0)


        // root_layout.background is set as a GradientDrawable in layout xml file
        val gradient = root_layout.background as GradientDrawable

        // animation
        val evaluator = ArgbEvaluator()
        val animator = TimeAnimator.ofFloat(0.0f, 1.0f)
        animator.duration = 5000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
        animator.addUpdateListener {
            val fraction = it.animatedFraction
            val newStart = evaluator.evaluate(fraction, gradStart, gradEnd) as Int
            val newMid = evaluator.evaluate(fraction, gradCenter, gradStart) as Int
            val newEnd = evaluator.evaluate(fraction, gradEnd, gradCenter) as Int

            gradient.colors = intArrayOf(newStart, newMid, newEnd)
        }

        animator.start()
    }

    // if sounds are playing, stop
    override fun onDestroy() {
        super.onDestroy()
        if (mp1.isLooping) {
            mp1.stop()
        }
        if (mp2.isLooping) {
            mp2.stop()
        }
        if (mp3.isLooping) {
            mp3.stop()
        }
        if (mp4.isLooping) {
            mp4.stop()
        }
        if (mp5.isLooping) {
            mp5.stop()
        }
        if (mp6.isLooping) {
            mp6.stop()
        }
        if (mp7.isLooping) {
            mp7.stop()
        }
        if (mp8.isLooping) {
            mp8.stop()
        }
    }

    // initialize the buttons and volume sliders
    private fun initButtonsSliders() {

        //Log.d("method call", "initButtonsSliders() called")
        val buttons = listOf<ImageView>(
            sound_1, sound_2,
            sound_3, sound_4,
            sound_5, sound_6,
            sound_7, sound_8
        )

        mp1 = MediaPlayer.create(this, R.raw.rain)
        mp2 = MediaPlayer.create(this, R.raw.wind)
        mp3 = MediaPlayer.create(this, R.raw.ocean)
        mp4 = MediaPlayer.create(this, R.raw.thunder)
        mp5 = MediaPlayer.create(this, R.raw.fire)
        mp6 = MediaPlayer.create(this, R.raw.hail)
        mp7 = MediaPlayer.create(this, R.raw.train)
        mp8 = MediaPlayer.create(this, R.raw.forest)

        for (button in buttons) {

            button.imageAlpha = 64

            button.setOnClickListener {

                when (button.id) {

                    R.id.sound_1 -> {
                        if (mp1.isLooping) {
                            mp1.pause()
                            mp1.isLooping = false
                            button.imageAlpha = 64
                            volBar_1.progress = 0
                            //Log.i("BTN", "Sound_1 paused")
                        }
                        else {
                            mp1.start()
                            mp1.isLooping = true
                            button.imageAlpha = 255
                            mp1.setVolume(0.5F, 0.5F)
                            volBar_1.progress = 50
                            //Log.i("BTN", "Sound_1 started")
                        }
                    }
                    R.id.sound_2 -> {
                        if (mp2.isLooping) {
                            mp2.pause()
                            mp2.isLooping = false
                            button.imageAlpha = 64
                            volBar_2.progress = 0
                            //Log.i("BTN", "Sound_2 paused")
                        }
                        else {
                            mp2.start()
                            mp2.isLooping = true
                            button.imageAlpha = 255
                            mp2.setVolume(0.5F, 0.5F)
                            volBar_2.progress = 50
                            //Log.i("BTN", "Sound_2 started")
                        }
                    }
                    R.id.sound_3 -> {
                        if (mp3.isLooping) {
                            mp3.pause()
                            mp3.isLooping = false
                            button.imageAlpha = 64
                            volBar_3.progress = 0
                            //Log.i("BTN", "Sound_3 paused")
                        }
                        else {
                            mp3.start()
                            mp3.isLooping = true
                            button.imageAlpha = 255
                            mp3.setVolume(0.5F, 0.5F)
                            volBar_3.progress = 50
                            //Log.i("BTN", "Sound_3 started")
                        }
                    }
                    R.id.sound_4 -> {
                        if (mp4.isLooping) {
                            mp4.pause()
                            mp4.isLooping = false
                            button.imageAlpha = 64
                            volBar_4.progress = 0
                            //Log.i("BTN", "Sound_4 paused")
                        }
                        else {
                            mp4.start()
                            mp4.isLooping = true
                            button.imageAlpha = 255
                            mp4.setVolume(0.5F, 0.5F)
                            volBar_4.progress = 50
                            //Log.i("BTN", "Sound_4 started")
                        }
                    }
                    R.id.sound_5 -> {
                        if (mp5.isLooping) {
                            mp5.pause()
                            mp5.isLooping = false
                            button.imageAlpha = 64
                            volBar_5.progress = 0
                            //Log.i("BTN", "Sound_5 paused")
                        }
                        else {
                            mp5.start()
                            mp5.isLooping = true
                            button.imageAlpha = 255
                            mp5.setVolume(0.5F, 0.5F)
                            volBar_5.progress = 50
                            //Log.i("BTN", "Sound_5 started")
                        }
                    }
                    R.id.sound_6 -> {
                        if (mp6.isLooping) {
                            mp6.pause()
                            mp6.isLooping = false
                            button.imageAlpha = 64
                            volBar_6.progress = 0
                            //Log.i("BTN", "Sound_1 paused")
                        }
                        else {
                            mp6.start()
                            mp6.isLooping = true
                            button.imageAlpha = 255
                            mp6.setVolume(0.5F, 0.5F)
                            volBar_6.progress = 50
                            //Log.i("BTN", "Sound_1 started")
                        }
                    }
                    R.id.sound_7 -> {
                        if (mp7.isLooping) {
                            mp7.pause()
                            mp7.isLooping = false
                            button.imageAlpha = 64
                            volBar_7.progress = 0
                            //Log.i("BTN", "Sound_7 paused")
                        }
                        else {
                            mp7.start()
                            mp7.isLooping = true
                            button.imageAlpha = 255
                            mp7.setVolume(0.5F, 0.5F)
                            volBar_7.progress = 50
                            //Log.i("BTN", "Sound_7 started")
                        }
                    }
                    R.id.sound_8 -> {
                        if (mp8.isLooping) {
                            mp8.pause()
                            mp8.isLooping = false
                            button.imageAlpha = 64
                            volBar_8.progress = 0
                            //Log.i("BTN", "Sound_8 paused")
                        }
                        else {
                            mp8.start()
                            mp8.isLooping = true
                            button.imageAlpha = 255
                            mp8.setVolume(0.5F, 0.5F)
                            volBar_8.progress = 50
                            //Log.i("BTN", "Sound_8 started")
                        }
                    }
                    //else -> Log.e("initButton error", "See initButton function in MainActivity")

                }
            }
        }

        val maxVolume = 100F

        val volSliders = listOf<SeekBar>(
            volBar_1, volBar_2,
            volBar_3, volBar_4,
            volBar_5, volBar_6,
            volBar_7, volBar_8
        )

        for (volSlider in volSliders) {

            volSlider.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    when (volSlider.id) {
                        R.id.volBar_1 -> {
                            val bar1vol = (log((maxVolume.toDouble() - (maxVolume - volSlider.progress).toDouble()), 10.0)) /
                                    log(maxVolume.toDouble(), 10.0)
                            mp1.setVolume(bar1vol.toFloat(), bar1vol.toFloat())
                        }
                        R.id.volBar_2 -> {
                            val bar2vol = (log((maxVolume.toDouble() - (maxVolume - volSlider.progress).toDouble()), 10.0)) /
                                    log(maxVolume.toDouble(), 10.0)
                            mp2.setVolume(bar2vol.toFloat(), bar2vol.toFloat())
                        }
                        R.id.volBar_3 -> {
                            val bar3vol = (log((maxVolume.toDouble() - (maxVolume - volSlider.progress).toDouble()), 10.0)) /
                                    log(maxVolume.toDouble(), 10.0)
                            mp3.setVolume(bar3vol.toFloat(), bar3vol.toFloat())
                        }
                        R.id.volBar_4 -> {
                            val bar4vol = (log((maxVolume.toDouble() - (maxVolume - volSlider.progress).toDouble()), 10.0)) /
                                    log(maxVolume.toDouble(), 10.0)
                            mp4.setVolume(bar4vol.toFloat(), bar4vol.toFloat())
                        }
                        R.id.volBar_5 -> {
                            val bar5vol = (log((maxVolume.toDouble() - (maxVolume - volSlider.progress).toDouble()), 10.0)) /
                                    log(maxVolume.toDouble(), 10.0)
                            mp5.setVolume(bar5vol.toFloat(), bar5vol.toFloat())
                        }
                        R.id.volBar_6 -> {
                            val bar6vol = (log((maxVolume.toDouble() - (maxVolume - volSlider.progress).toDouble()), 10.0)) /
                                    log(maxVolume.toDouble(), 10.0)
                            mp6.setVolume(bar6vol.toFloat(), bar6vol.toFloat())
                        }
                        R.id.volBar_7 -> {
                            val bar7vol = (log((maxVolume.toDouble() - (maxVolume - volSlider.progress).toDouble()), 10.0)) /
                                    log(maxVolume.toDouble(), 10.0)
                            mp7.setVolume(bar7vol.toFloat(), bar7vol.toFloat())
                        }
                        R.id.volBar_8 -> {
                            val bar8vol = (log((maxVolume.toDouble() - (maxVolume - volSlider.progress).toDouble()), 10.0)) /
                                    log(maxVolume.toDouble(), 10.0)
                            mp8.setVolume(bar8vol.toFloat(), bar8vol.toFloat())
                        }
                    }
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {
                }
                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            })
        }
    }

    // settings menu inflate
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // go to SettingsActivity
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
