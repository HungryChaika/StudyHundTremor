package com.example.studyhandtremor

import android.content.Context
import java.io.File
import java.io.FileOutputStream

class DataToFile {
    fun send(context: Context, fileName: String, data: String) {
        // /storage/emulated/0/Android/data/com.example.package/files/name
        val fos: FileOutputStream = FileOutputStream(File(context.getExternalFilesDir(null), fileName))
        fos.write(data.toByteArray());
        fos.close()
    }
}