package night.lines.todo.domain.task

import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import night.lines.todo.domain.DomainTestData
import night.lines.todo.domain.interactor.main.GetTasksUseCase
import night.lines.todo.domain.model.Task
import night.lines.todo.domain.repository.DatabaseRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetTasksUseCaseTest {
    private lateinit var databaseRepository: DatabaseRepository
    private lateinit var getTasksUseCase: GetTasksUseCase
    private lateinit var testSubscriber: TestSubscriber<ArrayList<Task>>

    @Before
    @Throws(Exception::class)
    fun setUp() {
        databaseRepository = Mockito.mock(DatabaseRepository::class.java)
        getTasksUseCase = GetTasksUseCase(databaseRepository)
        testSubscriber = TestSubscriber()
    }

    @Test
    @Throws(Exception::class)
    fun shouldReturnAllTasks() {
        val tasks = ArrayList<Task>()
        tasks.add(DomainTestData.TASK)

        Mockito.`when`(databaseRepository.getTasks(true)).thenReturn(Flowable.just(tasks))

        getTasksUseCase.execute(true).subscribe(testSubscriber)

        Mockito.verify(databaseRepository, Mockito.times(1)).getTasks(true)
        Mockito.verifyNoMoreInteractions(databaseRepository)

        testSubscriber.assertComplete()
        testSubscriber.assertValue(tasks)
    }
}