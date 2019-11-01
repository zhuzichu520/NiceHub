package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class TestPlugin : Plugin<Project> {
    override fun apply(project: Project) {

        project.tasks.register("helloWorld", MyTest::class.java) {
            this.say()
        }
    }
}