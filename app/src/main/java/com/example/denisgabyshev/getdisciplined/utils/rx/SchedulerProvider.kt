package com.example.denisgabyshev.getdisciplined.utils.rx

import io.reactivex.Scheduler

/**
 * Created by denisgabyshev on 10/09/2017.
 */
interface SchedulerProvider {
    fun ui(): Scheduler

    fun computation(): Scheduler

    fun io(): Scheduler
}