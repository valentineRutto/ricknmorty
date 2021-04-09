package com.example.ricknmorty.utils

import android.graphics.Bitmap
import androidx.palette.graphics.Palette

object ViewUtils {
    // Generate palette synchronously and return it
    fun createPaletteSync(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()


}