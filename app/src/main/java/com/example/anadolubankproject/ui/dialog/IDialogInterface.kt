package com.example.anadolubankproject.ui.dialog

/**
 * Created by gokalp.turgut on 15.2.2018.
 */
interface IDialogInterface {
    fun dismiss()

    interface OnDismissListener {

        fun onDismiss(dialog: IDialogInterface)
    }

    interface OnPositiveButtonClickListener {

        fun onClickPositiveButton(dialog: IDialogInterface)
    }

    interface OnNeutralButtonClickListener {

        fun onClickNeutralButton(dialog: IDialogInterface)
    }

    interface OnNegativeButtonClickListener {

        fun onClickNegativeButton(dialog: IDialogInterface)
    }
}