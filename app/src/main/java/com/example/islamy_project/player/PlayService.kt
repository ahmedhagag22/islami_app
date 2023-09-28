package com.example.islamy_project.player

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViews.RemoteView
import androidx.core.app.NotificationCompat
import com.example.islamy_project.MyApplication
import com.example.islamy_project.R

class PlayService : Service() {
    val binder = MyBinder()
    var mediPlayer = MediaPlayer()

    override fun onBind(intent: Intent?): IBinder? {
        return binder

    }

    inner class MyBinder : Binder() {

        fun getService(): PlayService {
            return this@PlayService
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        val urlToPlay = intent?.getStringExtra("url")
        val name = intent?.getStringExtra("name")
        val action = intent?.getStringExtra("action")

        if (urlToPlay != name && name != null)
            startMediaPlayer(urlToPlay, name)

        if (action != null)
            Log.e("action", action)

        if (action.equals("play")) {

            playPouseMediplayer()
        } else if (action.equals("stop")) {
            stopmediaPlayer()

        }
        return START_NOT_STICKY
    }

    var name: String = ""
    fun startMediaPlayer(urlToPlay: String?, name: String) {
        pauseMediaPlayer()
        this.name = name
        mediPlayer = MediaPlayer()
        mediPlayer.setDataSource(this, Uri.parse(urlToPlay))
        mediPlayer.prepareAsync()
        mediPlayer.setOnPreparedListener {
            it.start()
        }
        creatNotificationForMediaPlayer(name)

    }

    private fun playPouseMediplayer() {
        Log.e("action", "playPause")
        if (mediPlayer.isPlaying) {
            mediPlayer.pause()
        } else if (!mediPlayer.isPlaying) {
            mediPlayer.start()
        }
        updateNotifaction()


    }

    private fun stopmediaPlayer() {
        if (mediPlayer.isPlaying) {
            mediPlayer.stop()
            mediPlayer.reset()
        }
        stopForeground(true)
        stopSelf()
    }

    fun pauseMediaPlayer() {
        if (mediPlayer.isPlaying)
            mediPlayer.pause()
    }

    @SuppressLint("RemoteViewLayout")
    private fun creatNotificationForMediaPlayer(name: String) {
        val defultView = RemoteViews(packageName, R.layout.notificationview)
        defultView.setTextViewText(R.id.title, "islami App Radio")
        defultView.setTextViewText(R.id.desc, name)
        defultView.setImageViewResource(
            R.id.play,
            if (mediPlayer.isPlaying) R.drawable.ic_stop else R.drawable.play
        )

        defultView.setOnClickPendingIntent(R.id.play, getPlayButtonPendingIntent())
        defultView.setOnClickPendingIntent(R.id.stop, getStopButtonPendingIntent())


        var builder =
            NotificationCompat.Builder(this, MyApplication.RADIO_PLAYER_NOTIFICATION_CHANNEL)
                .setSmallIcon(R.drawable.ic_notofiacyion)
                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(defultView)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setSound(null)
        startForeground(20, builder.build())

    }

    private fun updateNotifaction() {
        val defaultView = RemoteViews(packageName, R.layout.notificationview)
        defaultView.setTextViewText(R.id.title, "islami App Radio")
        defaultView.setTextViewText(R.id.desc, name)
        defaultView.setImageViewResource(
            R.id.play,
            if (mediPlayer.isPlaying) R.drawable.ic_stop else R.drawable.play
        )
        defaultView.setOnClickPendingIntent(R.id.play, getPlayButtonPendingIntent())
        defaultView.setOnClickPendingIntent(R.id.stop, getStopButtonPendingIntent())

        var builder =
            NotificationCompat.Builder(this, MyApplication.RADIO_PLAYER_NOTIFICATION_CHANNEL)
                .setSmallIcon(R.drawable.ic_notofiacyion)
                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(defaultView)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setDefaults(0)
                .setSound(null)
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(20, builder.build())

    }

    val PLAY_ACTION = "play"
    val STOP_ACTION = "STOP"

    private fun getPlayButtonPendingIntent(): PendingIntent {
        val intent = Intent(this, PlayService::class.java)
        intent.putExtra("action", PLAY_ACTION)
        val pendingIntent = PendingIntent.getService(
            this,
            12, intent, PendingIntent.FLAG_IMMUTABLE
        )
        return pendingIntent
    }

    private fun getStopButtonPendingIntent(): PendingIntent {
        val intent = Intent(this, PlayService::class.java)
        intent.putExtra("action", STOP_ACTION)
        val pendingIntent = PendingIntent.getService(
            this, 0, intent, PendingIntent.FLAG_IMMUTABLE
        )
        return pendingIntent

    }


}