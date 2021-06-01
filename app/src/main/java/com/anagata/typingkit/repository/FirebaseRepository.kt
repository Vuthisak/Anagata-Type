package com.anagata.typingkit.repository

import android.content.Context
import android.util.Log
import com.anagata.typingkit.repository.database.DatabaseManager
import com.anagata.typingkit.repository.entity.FontEntity
import com.anagata.typingkit.repository.model.*
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.*
import java.io.*
import java.net.URL
import java.net.URLConnection


class FirebaseRepository(
    private val applicationContext: Context,
    private val databaseManager: DatabaseManager
) {

    private val fontDao = databaseManager.fontDao
    private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val ref = firebaseDatabase.reference

    fun initData(
        onCompleteListener: () -> Unit,
        onFailureListener: (ex: Exception) -> Unit
    ) {
        ref.get().addOnSuccessListener { data ->
            data.getValue(Typeface::class.java)?.let {
                CoroutineScope(Dispatchers.IO).launch {
                    it.typefaces.forEach { checkFont(it) }
                    onCompleteListener()
                }
            }
        }.addOnFailureListener {
            onFailureListener(it)
        }
    }

    fun getAllFontsFromDb(onCompleteListener: (List<Font>) -> Unit) {
        GlobalScope.launch {
            fontDao.getAllFonts()?.run {
                onCompleteListener(FontEntity.translateFontList(this))
            }
        }
    }

    private fun checkFont(font: Font) {
        val result = fontDao.getByName(font.name)
        if (result == null) {
            font.styles.forEach {
                downloadFont(font.name, it)
            }
            fontDao.insert(font.entity)
            Log.d("Inserted", font.name)
        }
    }

    private fun downloadFont(fontName: String, fontWeight: Style?) {
        fontWeight?.let {
            val path = "${applicationContext.cacheDir}/anagata/$fontName-${it.name}.otf"
            downloadFile(it.src, path)
            it.location = path
        }
    }

    private fun downloadFile(url: String, path: String) {
        try {
            val folder = File(applicationContext.cacheDir, "anagata")
            folder.mkdirs()
            val outputFile = File(path)
            val u = URL(url)
            val conn: URLConnection = u.openConnection()
            val contentLength: Int = conn.contentLength
            val stream = DataInputStream(u.openStream())
            val buffer = ByteArray(contentLength)
            stream.readFully(buffer)
            stream.close()
            val fos = DataOutputStream(FileOutputStream(outputFile))
            fos.write(buffer)
            fos.flush()
            fos.close()
        } catch (e: FileNotFoundException) {
            return  // swallow a 404
        } catch (e: IOException) {
            return  // swallow a 404
        }
    }

}
