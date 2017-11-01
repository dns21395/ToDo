package com.example.denisgabyshev.getdisciplined.data.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import android.media.audiofx.AudioEffect

/**
 * Created by denisgabyshev on 18/09/2017.
 */
@Entity(foreignKeys = arrayOf(ForeignKey(entity = Date::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("dateId"),
        onDelete = ForeignKey.CASCADE)))
data class Task  (
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        val dateId: Long,
        var task: String,
        var taskOrder: Int,
        var status: Boolean = false
)