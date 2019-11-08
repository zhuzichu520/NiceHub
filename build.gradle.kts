Config.init(project)

project.ext.set("kotlin_version", "1.3.50")

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Dep.pluginBuildGradle)
        classpath(Dep.pluginkotlinGradle)
        classpath(Dep.pluginNavigationSafe)
        classpath("com.github.dcendents:android-maven-gradle-plugin:2.1")
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