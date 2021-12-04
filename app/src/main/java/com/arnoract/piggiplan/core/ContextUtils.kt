package com.arnoract.piggiplan.core

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.pm.PackageInfoCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.arnoract.piggiplan.R

fun Context.toast(
	message: CharSequence,
	length: Int = Toast.LENGTH_LONG
) {
    val inflater = LayoutInflater.from(this)
    val layout = inflater.inflate(R.layout.toast_alert, null, false)
    val text: TextView = layout.findViewById(R.id.toastTextView)
    text.text = message
    with(Toast(applicationContext)) {
        duration = length
        view = layout
        show()
    }
}

fun Context.appVersionCode(): Long {
    return try {
        val packageInfo = applicationContext.packageManager.getPackageInfo(
			applicationContext.packageName,
			0
		)
        PackageInfoCompat.getLongVersionCode(packageInfo)
    } catch (e: PackageManager.NameNotFoundException) {
        throw RuntimeException("Could not get package name: $e")
    }
}

fun Context.appVersionName(): String {
    return try {
        val packageInfo = applicationContext.packageManager.getPackageInfo(
			applicationContext.packageName,
			0
		)
        packageInfo.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        throw RuntimeException("Could not get package name: $e")
    }
}

fun Context.isDebug(): Boolean {
    return 0 != this.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
}

fun Fragment.findNavControllerSafety(currentId: Int): NavController? {
    try {
        val controller = findNavController()

        if (controller.currentDestination?.id != currentId) {
            return null
        }
        return controller
    } catch (e: Exception) {
        return null
    }
}
