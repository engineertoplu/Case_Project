package com.example.anadolubankproject.ui.ktx

import android.app.Activity
import android.content.DialogInterface
import android.text.Html
import android.view.Gravity
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import com.example.anadolubankproject.R

/** success Dialog *
 * setting success image
 * @param message : Nonnull message of dialog
 * @param title: Nullable title if null -> visibility gone
 * @param messageGravity: message gravity -> predefined center
 * @param positiveListener: @nullable  if null -> dialog.dissmiss
 */
fun Activity?.showSuccessDialog(message: String, title: String? = null, messageGravity: Int = Gravity.CENTER_HORIZONTAL,
                                positiveListener: DialogInterface.OnClickListener): AlertDialog? {
    if (this?.isFinishing != false) return null
    return AlertDialog.Builder(this)
            .setPositiveButton(R.string.ok, positiveListener)
            .setMessage(message)
            .setTitle(title)
            .show()
}

/** Error Dialog *
 * setting error image
 * @param message : Nonnull message
 * @param title: Nullable title if null -> visibility gone
 * @param messageGravity: message gravity -> predefined center
 * @param positiveListener: @nullable  if null -> dialog.dissmiss
 */
fun Activity?.showErrorDialog(message: String, title: String? = null, messageGravity: Int = Gravity.CENTER_HORIZONTAL,
                              positiveListener: DialogInterface.OnClickListener): AlertDialog? {
    if (this?.isFinishing != false) return null
    return AlertDialog.Builder(this)
            .setPositiveButton(R.string.ok, positiveListener)
            .setMessage(message)
            .setTitle(title)
            .show()
}

/** Info Dialog *
 * @param message : @nonnull message
 * @param title: @nullable title if null -> visibility gone
 * @param messageGravity: message gravity -> predefined left
 * @param positiveListener: @nullable  if null -> dialog.dissmiss
 * @param positiveButtonText: @nullable if null -> r.string.ok
 */
fun Activity?.showInfoDialog(message: String, title: String? = null, messageGravity: Int = Gravity.LEFT, makeHtml: Boolean = false,
                             positiveButtonText: String? = null, positiveListener:  DialogInterface.OnClickListener? = null): AlertDialog? {
    if (this?.isFinishing != false) return null
    return AlertDialog.Builder(this)
            .setPositiveButton(positiveButtonText, positiveListener)
            .setMessage(Html.fromHtml(message))
            .setTitle(title)
            .show()
}

/** Retry Dialog *
 * @param message : @nonnull message
 * @param title: @nullable title if null -> visibility gone
 * @param positiveListener: @nullable  if null -> dialog.dissmiss
 * @param negativeListener: @nullable  if null -> dialog.dissmiss
 */
fun Activity?.showRetryDialog(
    message: String, title: String? = null,
    positiveListener: DialogInterface.OnClickListener?,
    negativeListener: DialogInterface.OnClickListener, @DrawableRes imageResId: Int? = null): AlertDialog? {
    if (this?.isFinishing != false) return null
    return AlertDialog.Builder(this)
        .setPositiveButton(R.string.action_retry, positiveListener)
        .setNegativeButton(R.string.cancel, negativeListener)
        .setMessage(message)
        .setTitle(title)
        .setIcon(imageResId!!)
        .show()
}
