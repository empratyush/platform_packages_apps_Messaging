package com.android.messaging

import android.Manifest
import android.app.Activity
import android.app.Notification
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

fun sendNotificationIfPermissionIsGranted(
    context: Context, tag: String?, id: Int, notification: Notification
): Boolean {

    if (ActivityCompat.checkSelfPermission(
            context, Manifest.permission.POST_NOTIFICATIONS
        ) != PackageManager.PERMISSION_GRANTED
    ) return false

    NotificationManagerCompat.from(context).notify(
        tag, id, notification
    )
    return true
}

fun askNotificationPermissionIfNeeded(activity: Activity) {

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return

    if (ActivityCompat.checkSelfPermission(
            activity,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    ) return

    ActivityCompat.requestPermissions(
        activity,
        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
        0
    )
}
