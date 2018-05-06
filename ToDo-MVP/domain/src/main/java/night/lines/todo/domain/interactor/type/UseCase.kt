package night.lines.todo.domain.interactor.type

import io.reactivex.Observable

interface UseCase<T> {
    fun execute(): Observable<T>
}