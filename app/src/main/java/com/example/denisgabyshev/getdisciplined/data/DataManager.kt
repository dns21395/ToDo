package com.example.denisgabyshev.getdisciplined.data

import com.example.denisgabyshev.getdisciplined.data.db.helpers.DateHelper
import com.example.denisgabyshev.getdisciplined.data.db.helpers.ListIdHelper
import com.example.denisgabyshev.getdisciplined.data.db.helpers.TaskHelper
import com.example.denisgabyshev.getdisciplined.data.db.model.Date
import com.example.denisgabyshev.getdisciplined.data.db.model.ListId
import com.example.denisgabyshev.getdisciplined.data.db.model.Task
import com.example.denisgabyshev.getdisciplined.data.prefs.PreferencesHelper
import io.reactivex.Flowable
import io.reactivex.Single


/**
 * Created by denisgabyshev on 11/09/2017.
 */
interface DataManager : PreferencesHelper, DateHelper, ListIdHelper, TaskHelper