package com.example.portableinventory.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import com.example.portableinventory.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

fun Context.checkForPermissions(permissions: List<String>, successAction: () -> Unit) {
    Dexter.withContext(this)
        .withPermissions(
            permissions
        )
        .withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                report?.let {
                    if (it.areAllPermissionsGranted()) {
                        successAction()
                    } else {
                        showSettingsDialog(
                            getString(R.string.setting_dialog_title),
                            getString(R.string.setting_dialog_message)
                        )
                    }
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                token: PermissionToken?
            ) {
                token?.continuePermissionRequest()
            }

        })
        .check()
}

private fun Context.openSettings() {
    (this as Activity).startActivityForResult(
        Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        ).apply {
            data = Uri.fromParts("package", packageName, null)
        },
        SETTINGS_REQUEST_CODE
    )
}

fun Context.showSettingsDialog(title: String, message: String) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(getString(R.string.open_settings_button)) { dialog, _ ->
            dialog.dismiss()
            openSettings()
        }
        .setNegativeButton(getString(R.string.negative_button)) { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}
