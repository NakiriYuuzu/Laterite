package tw.edu.pu.function

import android.media.MediaPlayer
import android.util.Log
import androidx.activity.ComponentActivity

class MusicPlayer(private val context: ComponentActivity) {
    private lateinit var mp: MediaPlayer

    fun playMusic(music: Int) {
        mp = MediaPlayer.create(context, music)
        mp.start()
    }

    fun stopMusic() {
        mp.stop()
    }
}