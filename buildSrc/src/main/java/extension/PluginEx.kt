package extension

import org.gradle.api.Action
import org.gradle.api.Project
import plugin.extension.StudentExtension

fun Project.student(configure: Action<StudentExtension>) {
    configure.execute(this.extensions.getByType(StudentExtension::class.java))
}