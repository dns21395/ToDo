package night.lines.todo.domain.interactor.type

import io.reactivex.Observable

interface UseCaseWithParameter<P, R> {
    fun execute(parameter: P): Observable<R>
}