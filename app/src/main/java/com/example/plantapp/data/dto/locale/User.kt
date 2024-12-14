package com.example.plantapp.data.dto.locale

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "USER")
data class User(
    @ColumnInfo(name ="date")
    var date: String,

    @ColumnInfo(name ="option")
    var premiumOption:Int?,

    @ColumnInfo(name ="payment")
    var payment:Double)
{
    @PrimaryKey(autoGenerate = true)
    var id :Int =0
}