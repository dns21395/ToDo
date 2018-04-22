package night.lines.todo.domain.interactor.type

import io.reactivex.Flowable

interface FlowableUseCaseWithParameter<P, R> {
    fun execute(parameter: P): Flowable<R>
}