package plugin.test.ext

import org.gradle.api.Project
import org.gradle.kotlin.dsl.*

open class StudentExtension(
    var nickname: String = "default",
    var age: Int = -1
)

fun Project.kotlinProject() {
    dependencies {
        "comp"(kotlin("stdlib"))
    }
}