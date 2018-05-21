package night.lines.todo.data

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import io.reactivex.BackpressureStrategy
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import night.lines.todo.data.database.db.AppDatabase
import night.lines.todo.data.database.db.converter.DatabaseConverterImpl
import night.lines.todo.data.database.db.dao.TaskDao
import night.lines.todo.data.database.db.manager.DatabaseRepositoryImpl
import night.lines.todo.data.database.db.model.TaskModel
import night.lines.todo.domain.model.Task
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseRepositoryImplTest {

    private val TAG = "databasetest"

    private lateinit var database: AppDatabase
    private lateinit var taskDao: TaskDao
    private lateinit var databaseRepositoryImpl: DatabaseRepositoryImpl
    private lateinit var databaseConverterImpl: DatabaseConverterImpl
    private lateinit var testSubscriber: TestObserver<TaskModel>

    @Before
    @Throws(Exception::class)
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), AppDatabase::class.java).build()
        databaseConverterImpl = DatabaseConverterImpl()
        taskDao = database.taskDao()
        testSubscriber = TestObserver()
        databaseRepositoryImpl = DatabaseRepositoryImpl(databaseConverterImpl, taskDao)
    }

    @Test
    @Throws(Exception::class)
    fun shouldInsertNewTask() {
        val task = Task(DataTestData.TASK_ID, 0, DataTestData.TASK_NAME, DataTestData.TASK_DATE)

        databaseRepositoryImpl.insertTask(task)
                .map { taskDao.getTaskByDate(DataTestData.TASK_DATE) }
                .subscribe(testSubscriber)

        testSubscriber.assertValue {
            it.taskName == task.taskName &&
                    it.date == task.date &&
                    it.isDone == task.isDone
        }

    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        database.close()
    }
}