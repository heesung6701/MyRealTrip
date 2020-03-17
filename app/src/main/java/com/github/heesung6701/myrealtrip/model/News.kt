package com.github.heesung6701.myrealtrip.model

import android.os.Parcel
import android.os.Parcelable

data class News(val thumbnail: String, val title: String, val content: String) : Parcelable{
    val keywords : List<String>

    init {
        keywords = listOf("dummy1", "dummy2", "dummy3")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(thumbnail)
        dest.writeString(title)
        dest.writeString(content)
    }

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<News> {
        const val INTENT_NAME: String = "News"

        override fun createFromParcel(parcel: Parcel): News {
            return News(parcel)
        }

        override fun newArray(size: Int): Array<News?> {
            return arrayOfNulls(size)
        }
    }
}