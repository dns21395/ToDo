package com.example.denisgabyshev.getdisciplined.data.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

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
        val task: String,
        val order: Int
)