package com.raqust.bluko.common.wrapper

import android.app.Activity
//import com.afollestad.materialdialogs.DialogAction
//import com.afollestad.materialdialogs.MaterialDialog

/**
 * Created by linzehao
 * time: 2018/5/17.
 * info:
 */
class DialogImpl(activity: Activity, title: String, content: String, positive: String, negative: String,
                 positiveCallback: (() -> Unit)?,check:String="", checkCallback: ((Boolean) -> Unit)? = null, negativeCallback: (() -> Unit)? = null) {
    init {
        try {
//            val builder = MaterialDialog.Builder(activity)
//                    .cancelable(false)
//                    .title(title)
//                    .content(content)
//                    .positiveText(positive)
//                    .negativeText(negative)
//
//                    .onAny({ _, which ->
//                        when (which) {
//                            DialogAction.POSITIVE -> {
//                                positiveCallback?.invoke()
//                            }
//                            DialogAction.NEGATIVE -> {
//                                negativeCallback?.invoke()
//                            }
//                        }
//                    })
//            if (checkCallback != null) {
//                builder.checkBoxPrompt(check, false, { _, isCheck ->
//                    checkCallback.invoke(isCheck)
//                })
//            }
//
//            builder.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}