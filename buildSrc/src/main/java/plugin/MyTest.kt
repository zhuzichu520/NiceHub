package plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class MyTest : DefaultTask() {
    var str: String = ""

    @TaskAction
    fun say() {
        str="123456"
        print(str)
    }
}