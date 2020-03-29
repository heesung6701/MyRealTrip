package com.github.heesung6701.myrealtrip.model

import java.util.*
import kotlin.collections.ArrayList

data class News(val link: String, val title: String){
    var keywords: List<String> = emptyList()

    var content: String? = null
    set(value) {
        value?.let {
            if(it.isBlank()) return@let
            updateKeyword(it)
        }
        field = value
    }
    var thumbnail: String? = null

    private fun updateKeyword(content: String){
        val items = content.split("[^ㄱ-ㅎ^가-힣ㅏ-ㅣa-zA-Z0-9]".toRegex()).fold(HashMap<String, Int>()) { acc, str ->
            if(str=="") return@fold acc
            acc[str]?.plus(1) ?: acc.put(str, 1)
            return@fold acc
        }

        val pq = PriorityQueue<Pair<String, Int>>(items.size, compareBy {
            -it.second
        })
        for ((k, v) in items) {
            pq.add(Pair(k, v))
        }
        val list: ArrayList<String> = ArrayList(3)
        for (i in 1..3) {
            list.add(pq.poll()?.first ?: "")
        }
        keywords = list.toList()
    }
}