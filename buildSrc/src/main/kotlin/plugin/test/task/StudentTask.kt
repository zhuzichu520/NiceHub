package plugin.test.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import plugin.test.ext.StudentExtension

open class StudentTask : DefaultTask() {

    init {
        group = "nicehub"
        description = "this StudentTask"
    }

    @TaskAction
    fun doAction() {
        val nickname = project.extensions.getByType(StudentExtension::class.java).nickname
        val age = project.extensions.getByType(StudentExtension::class.java).age
        println("我是:$nickname,今年${age}岁")
    }
}