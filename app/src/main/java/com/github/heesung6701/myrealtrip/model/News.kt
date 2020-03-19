package com.github.heesung6701.myrealtrip.model

import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

data class News(val link: String, val title: String, val content: String, val thumbnail: String){
    var keywords: List<String>

    init {
        val items = content.split(" ").fold(HashMap<String, Int>()) { acc, str ->
            acc[str]?.plus(1) ?: acc.put(str, 1)
            return@fold acc
        }
        val pq = PriorityQueue<Pair<String, Int>>(items.size, compareBy {
            -it.second
        })
        for ((k, v) in items) {
            pq.add(Pair<String, Int>(k, v))
        }
        val list: ArrayList<String> = ArrayList<String>(3)
        for (i in 1..3) {
            if (pq.isEmpty()) {
                list.add("")
                continue
            }
            list.add(pq.poll().first)
        }
        keywords = list.toList()
        Log.e("~~~~", keywords.toString())
    }
}