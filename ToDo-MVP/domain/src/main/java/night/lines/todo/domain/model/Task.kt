package night.lines.todo.domain.model

data class Task (
        val id: Long,
        val taskName: String,
        var date: Long,
        var isDone: Boolean = false
)

