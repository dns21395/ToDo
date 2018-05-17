//package night.lines.todo.toothpick.module
//
//import night.lines.todo.domain.interactor.main.AddTaskUseCase
//import night.lines.todo.domain.interactor.main.GetTasksUseCase
//import night.lines.todo.domain.interactor.main.RemoveTaskUseCase
//import night.lines.todo.domain.interactor.main.UpdateTaskUseCase
//import night.lines.todo.toothpick.provider.usecase.AddTaskUseCaseProvider
//import night.lines.todo.toothpick.provider.usecase.GetTasksUseCaseProvider
//import night.lines.todo.toothpick.provider.usecase.RemoveTaskUseCaseProvider
//import night.lines.todo.toothpick.provider.usecase.UpdateTaskUseCaseProvider
//import toothpick.config.Module
//
//class UseCaseModule : Module() {
//    init {
//        bind(AddTaskUseCase::class.java).toProvider(AddTaskUseCaseProvider::class.java)
//        bind(GetTasksUseCase::class.java).toProvider(GetTasksUseCaseProvider::class.java)
//        bind(UpdateTaskUseCase::class.java).toProvider(UpdateTaskUseCaseProvider::class.java)
//        bind(RemoveTaskUseCase::class.java).toProvider(RemoveTaskUseCaseProvider::class.java)
//
//    }
//}