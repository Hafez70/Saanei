package com.folioreader.ui.folio.adapter

import android.text.TextUtils
import android.util.Log

enum class ListViewType(val value: Int) {

    UNKNOWN_VIEW(0),
    INIT_VIEW(1),
    LOADING_VIEW(2),
    NORMAL_VIEW(3),
    EMPTY_VIEW(4),
    FAILURE_VIEW(5);

    companion object {
        const val KEY = "LIST_VIEW_TYPE"

        @JvmStatic
        fun fromString(string: String?): ListViewType {

            if (TextUtils.isEmpty(string)) {
                return UNKNOWN_VIEW
            }

            return try {
                valueOf(string!!)
            } catch (e: Exception) {

                UNKNOWN_VIEW
            }
        }
    }
}