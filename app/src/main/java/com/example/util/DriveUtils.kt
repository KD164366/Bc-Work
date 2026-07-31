package com.example.util

import android.content.Context
import android.content.Intent
import android.net.Uri

object DriveUtils {

    fun extractDriveFileId(url: String): String? {
        val regex = Regex("/file/d/([a-zA-Z0-9_-]+)")
        val match = regex.find(url)
        if (match != null && match.groupValues.size > 1) {
            return match.groupValues[1]
        }
        val idRegex = Regex("id=([a-zA-Z0-9_-]+)")
        val idMatch = idRegex.find(url)
        return idMatch?.groupValues?.get(1)
    }

    fun getPreviewUrl(url: String): String {
        val fileId = extractDriveFileId(url)
        return if (fileId != null) {
            "https://drive.google.com/file/d/$fileId/preview"
        } else {
            url
        }
    }

    fun getDownloadUrl(url: String): String {
        val fileId = extractDriveFileId(url)
        return if (fileId != null) {
            "https://drive.google.com/uc?export=download&id=$fileId"
        } else {
            url
        }
    }

    fun openInBrowserOrDownload(context: Context, url: String) {
        val downloadUrl = getDownloadUrl(url)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(downloadUrl)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            val fallbackIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(fallbackIntent)
        }
    }

    fun openWhatsapp(context: Context, phoneNumber: String, message: String) {
        val cleanNumber = phoneNumber.replace(Regex("[^0-9]"), "")
        val formattedNumber = if (cleanNumber.startsWith("0")) {
            "94" + cleanNumber.substring(1)
        } else if (!cleanNumber.startsWith("94")) {
            "94" + cleanNumber
        } else {
            cleanNumber
        }

        val encodedMsg = Uri.encode(message)
        val whatsappUri = Uri.parse("https://api.whatsapp.com/send?phone=$formattedNumber&text=$encodedMsg")
        val intent = Intent(Intent.ACTION_VIEW, whatsappUri).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
