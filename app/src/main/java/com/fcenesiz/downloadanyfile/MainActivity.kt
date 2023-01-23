package com.fcenesiz.downloadanyfile

import android.app.DownloadManager
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fcenesiz.downloadanyfile.ui.theme.DownloadAnyFileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val downloader = AndroidDownloader(this)
        registerReceiver(DownloadCompletedReceiver(), IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        );

        setContent {
            DownloadAnyFileTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(onClick = {
                        downloader.downloadFile("https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Sheba1.JPG/800px-Sheba1.JPG")
                    }) {
                        Text(
                            text = "Download Image File"
                        )
                    }
                }
            }
        }
    }
}