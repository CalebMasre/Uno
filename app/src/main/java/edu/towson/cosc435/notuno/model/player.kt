package edu.towson.cosc435.notuno.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//class representing player table/entity
@Entity(tableName = "players")
data class OnlinePlayer(

    //id attribute representing primary key
    @PrimaryKey
    val id: String,

    //image_url attribute
    @ColumnInfo(name = "image_url")
    val image_url: String? = null
)