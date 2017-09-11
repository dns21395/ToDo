package com.example.denisgabyshev.getdisciplined.data

import android.content.Context
import com.example.denisgabyshev.getdisciplined.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by denisgabyshev on 11/09/2017.
 */
@Singleton
class AppDataManager @Inject constructor(@ApplicationContext context: Context) : DataManager {

}