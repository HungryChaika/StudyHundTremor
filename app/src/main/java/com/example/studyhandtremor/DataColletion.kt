package com.example.studyhandtremor

import com.google.gson.Gson

class DataCollection {
    private val Data: MutableList<DataPattern> = mutableListOf()
    private var DataCollectionAllowed: Boolean = false

    fun convertDataToJson() = Gson().toJson(Data)

    fun addElemToData(Elems: FloatArray) {
        val Resalt = DataPattern(Elems[0], Elems[1], Elems[2])
        Data.add(Resalt)
    }

    fun clearData() {
        Data.clear()
    }

    fun allowDataCollection() {
        DataCollectionAllowed = true
    }

    fun prohibitDataCollection() {
        DataCollectionAllowed = false
    }

    fun getFlagDataCollectionAllowed(): Boolean = DataCollectionAllowed

}

class DataPattern(val x: Float, val y: Float, val z: Float) {
    override fun toString(): String = "X: $x , Y: $y , Z: $z"
}

/*   Из Json В List
val itemsListType: Type = object : TypeToken<List<DataPattern?>?>() {}.type
val elem: List<DataPattern> = Gson().fromJson(DataJson, itemsListType)
println(elem.toString())
*/