package com.github.heesung6701.myrealtrip.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class News(val title: String, val content: String, val thumbnail: String) : Parcelable{
    init {
        updateKeyword(content)
    }
    var keywords: List<String>? = null

    private fun updateKeyword(content: String){
        val items = content.split(" ").fold(HashMap<String, Int>()){ acc, str ->
            acc[str]?.plus(1)?: acc.put(str, 1)
            return
        }
        /*val queue = content.split(" ").fold(PriorityQueue<Pair<String, Int>>()) { q : PriorityQueue<Pair<String, Int>>, str ->
            val cnt = q.
            q.put(Pair(str, )
            q.put(str)
        }*/
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