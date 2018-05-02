package night.lines.todo.domain

import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import night.lines.todo.domain.interactor.main.AddTaskUseCase
import night.lines.todo.domain.model.Task
import night.lines.todo.domain.repository.DatabaseRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class AddTaskUseCaseTest {
    private lateinit var databaseRepository: DatabaseRepository
    private lateinit var addTaskUseCase: AddTaskUseCase
    private lateinit var testSubscriber: TestObserver<Long>

    @Before
    @Throws(Exception::class)
    fun setUp() {
        databaseRepository = Mockito.mock(DatabaseRepository::class.java)
        addTaskUseCase = AddTaskUseCase(databaseRepository)
        testSubscriber = TestObserver()
    }

    @Test
    @Throws(Exception::class)
    fun shouldAddNewTask() {
        Mockito.`when`(databaseRepository.insertTask(any())).thenReturn(Observable.just(1))
        addTaskUseCase.execute(Task(1, "test", 0)).subscribe(testSubscriber)

        Mockito.verify(databaseRepository, Mockito.times(1)).insertTask(Task(1, "test", 0))
        Mockito.verifyNoMoreInteractions(databaseRepository)

        testSubscriber.assertComplete()
        testSubscriber.assertValue(1)
    }

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }
    private fun <T> uninitialized(): T = null as T
}