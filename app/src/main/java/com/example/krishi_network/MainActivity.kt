package com.example.krishi_network

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    val api_key = "AIzaSyCZZ8_YMUFPRTcvHTUZ-6Y2_brRlhiTjbk"
    var videoIdx = arrayOf<String>("IEF6mw7eK4s", "8CEJoCr_9UI", "3-nM3yNi3wg","kJQP7kiw5Fk","pRpeEdMmmQ0","RgKAFK5djSk","yU0tnrEk8H4")
    var list:List<String> = videoIdx.toList()
    lateinit var youTubePlayerInit : YouTubePlayer.OnInitializedListener
    lateinit var youTubePlayer: YouTubePlayer
    val random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        youtubePlayerSetup()
        btnPlay.setOnClickListener {
            var idx = videoIdx[random.nextInt(videoIdx.size-1)]
            youTubePlayer.cueVideo(videoIdx[random.nextInt(videoIdx.size-1)])
        }

    }

    private fun youtubePlayerSetup() {
        ytPlayer.initialize(api_key, object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                youTubePlayer= p1!!
                p1.cueVideo(videoIdx[random.nextInt(videoIdx.size-1)])
                p1?.play()
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(applicationContext,"Error", Toast.LENGTH_SHORT).show()
            }

        })
    }
}