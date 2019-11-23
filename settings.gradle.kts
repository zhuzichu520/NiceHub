include(":app", ":shared")
rootProject.name = "NiceHub"

//val dest = "kotlin.build.gradle.kts"
val dest = "nodsl.build.gradle.kts"
//val dest = "groovy.build.gradle"

rootProject.children.forEach {
    if (it.name == "app") {
        it.buildFileName = dest
    }
}
//https://github.com/gradle/gradle/issues/11090
//https://docs.gradle.org/current/userguide/upgrading_version_5.html#buildsrc_usage_in_gradle_settings
//gradle6.0 之后 buildSrc在setting.gradle后面执行
//gradle.projectsLoaded {
//    Config.init(this.rootProject)
//}