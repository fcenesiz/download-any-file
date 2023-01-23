package com.fcenesiz.downloadanyfile

import java.net.URL

interface Downloader {

    fun downloadFile(url: String) : Long

}