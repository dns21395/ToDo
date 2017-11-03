package com.example.denisgabyshev.getdisciplined.data.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by denisgabyshev on 03/11/2017.
 */
@Entity
data class ListId(
   @PrimaryKey(autoGenerate = true)
   val id: Long,
   var name: String
)