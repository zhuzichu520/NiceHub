package plugin.test

import org.gradle.api.Plugin
import org.gradle.api.Project
import plugin.test.ext.StudentExtension
import plugin.test.task.StudentTask

class TestPlugin : Plugin<Project> {

    override fun apply( project: Project) {
        Log.l("TestPlugin","调用了apply()")
        val extension = project.extensions.create("student", StudentExtension::class.java)
        project.tasks.register("printStudent", StudentTask::class.java) {
            doLast {
                println("我的昵称是${extension.nickname}")
            }
        }
    }
}