package night.lines.todo.domain.task

import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import night.lines.todo.domain.DomainTestData
import night.lines.todo.domain.interactor.main.UpdateTaskUseCase
import night.lines.todo.domain.repository.DatabaseRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class UpdateTaskUseCaseTest {
    private lateinit var updateTaskUseCase: UpdateTaskUseCase
    private lateinit var databaseRepository: DatabaseRepository
    private lateinit var testSubscriber: TestObserver<Unit>

    @Before
    @Throws(Exception::class)
    fun setUp() {
        databaseRepository = Mockito.mock(DatabaseRepository::class.java)
        updateTaskUseCase = UpdateTaskUseCase(databaseRepository)
        testSubscriber = TestObserver()
    }

    @Test
    @Throws(Exception::class)
    fun shouldUpdateTask() {
        Mockito.`when`(databaseRepository.updateTask(any())).thenReturn(Observable.empty())

        databaseRepository.updateTask(DomainTestData.TASK).subscribe(testSubscriber)

        Mockito.verify(databaseRepository, Mockito.times(1)).updateTask(DomainTestData.TASK)
        Mockito.verifyNoMoreInteractions(databaseRepository)

        testSubscriber.onComplete()

    }

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }
    private fun <T> uninitialized(): T = null as T
}