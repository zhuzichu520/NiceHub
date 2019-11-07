package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import plugin.extension.StudentExtension

class TestPlugin : Plugin<Project> {

  override fun apply( project: Project) {
      println("调用了apply方法")
        val extension = project.extensions.create("student", StudentExtension::class.java)
        project.tasks.register("printStudent", StudentTask::class.java) {
            doLast {
                println("我的昵称是${extension.nickname}")
            }
        }
    }
}