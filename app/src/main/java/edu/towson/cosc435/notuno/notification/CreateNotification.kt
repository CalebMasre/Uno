package edu.towson.cosc435.notuno.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.compose.ui.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import edu.towson.cosc435.notuno.R


class CreateNotification(
    private val ctx: Context,
    private val title: String,
    private val msg: String
) {

    private val channelId = ""
    private val channelName = "My-channel"


    //function that builds and shows notification notification
    fun showNotification() {
        //builds notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "description"
            }
            val notificationManager =
                ctx.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }

        //builds notifications
        val notificationBuilder = NotificationCompat.Builder(ctx, channelId)
            .setSmallIcon(R.drawable.notunologo)
            .setColor(Color.Red.hashCode())
            .setContentTitle(title)
            .setContentText(msg)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        NotificationManagerCompat.from(ctx).notify(0, notificationBuilder.build())

    }//end of showNotification

}//end of CreateNotification class