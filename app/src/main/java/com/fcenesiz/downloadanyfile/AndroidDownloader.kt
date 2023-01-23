package com.fcenesiz.downloadanyfile

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.net.toUri

class AndroidDownloader(
    private val context: Context
) : Downloader {

    companion object{
        public val TAG: String = AndroidDownloader::class.simpleName.toString()
    }

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String): Long {
        var downloadReference = 0L
        try {
            val request = DownloadManager.Request(url.toUri())
                .setMimeType("image/jpeg")
                .setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_WIFI
                            or DownloadManager.Request.NETWORK_MOBILE
                )
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setTitle("image.jpg")
                .addRequestHeader("Authorization", "Bearer <token>")
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image.jpg")
            downloadReference = downloadManager.enqueue(request)
        } catch (e: IllegalArgumentException) {
            Log.i(TAG, "${e.message}")
        }
        return downloadReference
    }
}