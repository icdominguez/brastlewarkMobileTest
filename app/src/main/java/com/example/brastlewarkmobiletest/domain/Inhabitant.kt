package com.example.brastlewarkmobiletest.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "inhabitants")
data class Inhabitant (
    @Ignore
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("thumbnail")
    var thumbnail: String,
    @SerializedName("age")
    var age: Int,
    @SerializedName("weight")
    var weight: Double,
    @SerializedName("height")
    var height: Double,
    @SerializedName("hair_color")
    var hairColor: String,
    @SerializedName("professions")
    var professions : List<String>,
    @SerializedName("friends")
    var friends : List<String>
) {
    constructor() : this (0, "","",0, 0.0,0.0,"", emptyList(), emptyList())
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "employee_id")
    var employeeId: Int = 0
}