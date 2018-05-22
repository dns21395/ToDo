package night.lines.todo.domain.interactor.type

import io.reactivex.Flowable

interface FlowableUseCase<T> {
    fun execute(): Flowable<T>
}