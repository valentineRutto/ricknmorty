package com.valentinerutto.ricknmorty.utils

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import java.io.IOException
import java.net.URL

object ViewUtils {

    // Generate palette synchronously and return it
    fun createPaletteSync(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()

    fun Activity.makeAlertDialog(message: String, title: String): AlertDialog {
        // setup the alert builder
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(true)
        builder.setPositiveButton("OK", null)
        // create and show the alert dialog
        return builder.create()
    }

    fun Fragment.makeAlertDialog(message: String, title: String): AlertDialog {
        return requireActivity().makeAlertDialog(message, title)
    }
//
//    fun Fragment.makeProgressDialog(message: String): AlertDialog {
//        return requireActivity().makeProgressDialog(message)
//    }

    fun URL.toBitmap(): Bitmap? {
        return try {
            BitmapFactory.decodeStream(openStream())
        } catch (e: IOException) {
            null
        }
    }
}