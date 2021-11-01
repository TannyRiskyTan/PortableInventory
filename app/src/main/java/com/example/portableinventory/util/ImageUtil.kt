package com.example.portableinventory.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.portableinventory.BuildConfig
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

object ImageUtil {

    fun Fragment.takeImageFromCamera(resultCallback: ActivityResultLauncher<Uri?>, uri: Uri) {
        requireContext().checkForPermissions(
            listOf(Manifest.permission.CAMERA)
        ) { resultCallback.launch(uri) }
    }

    fun Fragment.takeImageFromGallery(launcher: ActivityResultLauncher<Intent>) {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        ).apply { type = "image/*" }
        if (galleryIntent.resolveActivity(requireActivity().packageManager) != null)
            requireContext().checkForPermissions(
                listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            ) { launcher.launch(galleryIntent) }
    }

    /**
     * @return Pair of the String filename, and the temporary Uri for the camera to save the image
     */
    fun Fragment.createSaveUri(): Pair<String, Uri?>? {
        val photoFile: File? = try {
            createImageFile()
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }

        return photoFile?.let { file ->
            Pair(
                file.name,
                FileProvider.getUriForFile(
                    requireContext(),
                    BuildConfig.APPLICATION_ID + ".fileprovider",
                    file
                )
            )
        }
    }

    @Throws(IOException::class)
    private fun Fragment.createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val storageDir: File =
            (requireActivity() as Activity).getExternalFilesDir(Environment.DIRECTORY_PICTURES) as File
        return File(storageDir, "$timeStamp.jpeg").apply { createNewFile() }
    }

    fun Fragment.copyImgFromUri(src: Uri, dest: Uri) {
        val cr = requireActivity().contentResolver
        val input = cr.openInputStream(src)
        cr.openOutputStream(dest)?.use {
            input?.copyTo(it)
        }
    }

    fun getUriForFilename(context: Context, filename: String): Uri {
        val storageDir =
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) as File
        return FileProvider.getUriForFile(
            context,
            BuildConfig.APPLICATION_ID + ".fileprovider",
            File(storageDir, filename)
        )
    }

    fun Fragment.getBitMapFromUri(photoUri: Uri): Bitmap? {
        val cr = requireActivity().contentResolver
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            try {
                return MediaStore.Images.Media.getBitmap(cr, photoUri)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            try {
                val source = ImageDecoder.createSource(cr, photoUri)
                return ImageDecoder.decodeBitmap(source)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return null
    }
}