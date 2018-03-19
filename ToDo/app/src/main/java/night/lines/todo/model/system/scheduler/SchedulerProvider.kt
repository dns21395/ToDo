package night.lines.todo.model.system.scheduler

import io.reactivex.*

/**
 * Created by denisgabyshev on 19/03/2018.
 */
interface SchedulerProvider {
    fun <T> ioToMainObservableScheduler(): ObservableTransformer<T, T>

    fun <T> ioToMainSingleScheduler(): SingleTransformer<T, T>

    fun ioToMainCompletableScheduler(): CompletableTransformer

    fun <T> ioToMainFlowableScheduler(): FlowableTransformer<T, T>

    fun <T> ioToMainMaybeScheduler(): MaybeTransformer<T, T>
}