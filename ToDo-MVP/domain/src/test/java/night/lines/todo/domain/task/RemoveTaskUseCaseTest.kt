package night.lines.todo.domain.task

import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import night.lines.todo.domain.DomainTestData
import night.lines.todo.domain.interactor.main.RemoveTaskUseCase
import night.lines.todo.domain.model.Task
import night.lines.todo.domain.repository.DatabaseRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class RemoveTaskUseCaseTest {
    private lateinit var databaseRepository: DatabaseRepository
    private lateinit var removeTaskUseCase: RemoveTaskUseCase
    private lateinit var testSubscriber: TestObserver<Unit>

    @Before
    @Throws(Exception::class)
    fun setUp() {
        databaseRepository = Mockito.mock(DatabaseRepository::class.java)
        removeTaskUseCase = RemoveTaskUseCase(databaseRepository)
        testSubscriber = TestObserver()
    }

    @Test
    @Throws(Exception::class)
    fun shouldRemoveTask() {
        Mockito.`when`(databaseRepository.removeTask(any())).thenReturn(Observable.empty())

        removeTaskUseCase.execute(DomainTestData.TASK).subscribe(testSubscriber)

        Mockito.verify(databaseRepository, Mockito.times(1)).removeTask(DomainTestData.TASK)
        Mockito.verifyNoMoreInteractions(databaseRepository)

        testSubscriber.assertComplete()
    }

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }
    private fun <T> uninitialized(): T = null as T
}