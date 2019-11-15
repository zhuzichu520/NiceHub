Config.init(project)

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Dep.pluginBuildGradle)
        classpath(Dep.pluginkotlinGradle)
        classpath(Dep.pluginNavigationSafe)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://dl.bintray.com/umsdk/release") }
    }
}

tasks {
    register<Delete>("clean") {
        delete(buildDir)
    }
}

gradle.beforeProject {

}

gradle.projectsEvaluated {

}
