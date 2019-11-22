import com.android.build.gradle.AppExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import plugin.test.ext.StudentExtension
import java.io.File

class InitApp(project: Project) {
    init {
        project.apply {
            plugin("com.android.application")
            plugin("org.jetbrains.kotlin.android")
            plugin("org.jetbrains.kotlin.android.extensions")
            plugin("org.jetbrains.kotlin.kapt")
            plugin("androidx.navigation.safeargs.kotlin")
            plugin("TestPlugin")
        }

        Config.initJenkinsProperties(project)

        val android = project.extensions.getByType(AppExtension::class.java)
        android.compileSdkVersion(Config.compileSdkVersion())

        android.signingConfigs {
            create("appSign") {
                keyAlias = Config.keyAlias()
                keyPassword = Config.keyPassword()
                storeFile = File(Config.storeFile())
                storePassword = Config.storePassword()
            }
        }

        android.defaultConfig {
            applicationId = Config.applicationId()
            minSdkVersion(Config.minSdkVersion())
            targetSdkVersion(Config.targetSdkVersion())
            versionCode = Config.versionCode()
            versionName = Config.versionName()
            signingConfig = android.signingConfigs.getByName("appSign")
            renderscriptTargetApi = 18
            renderscriptSupportModeEnabled = true
            resValue("string", "app_name_new", Config.appName())
            val fields = Config.getBuildConfigFields()
            fields.forEach {
                android.defaultConfig.buildConfigField(it[0], it[1], it[2])
            }
        }

        android.buildTypes {
            getByName("release") {
                isShrinkResources = true
                isMinifyEnabled = true
                isZipAlignEnabled = true
                proguardFiles(
                    android.getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
            getByName("debug") {

            }
        }

        android.compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }


        val kotlinJvmOptions =
            (android as ExtensionAware).extensions.getByType(KotlinJvmOptions::class.java)
        kotlinJvmOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()

        android.dataBinding {
            isEnabled = true
        }

        val androidExtensionsExtension =
            project.extensions.getByType(AndroidExtensionsExtension::class.java)
        androidExtensionsExtension.isExperimental = true

        android.sourceSets {
            android.sourceSets.getByName("main").java.srcDir("src/main/kotlin")
        }

        project.dependencies.apply {
            add("implementation", project.fileTree(mapOf("dir" to "libs", "include" to "*.jar")))
            add("api", project(mapOf("path" to ":shared")))
            add("kapt", Dep.kaptRoomCompiler)
            add("kapt", Dep.kaptDaggerCompiler)
            add("kapt", Dep.kaptDaggerProcessor)
            add("kapt", Dep.kaptGlide)
            add("kapt", Dep.kaptGlide)
            add("kapt", Dep.androidxDatabinding)
            add("debugImplementation", "com.squareup.leakcanary:leakcanary-android:2.0-beta-3")
            add("debugImplementation", "com.didichuxing.doraemonkit:doraemonkit:2.0.1")
        }

        val studentExtension = project.extensions.getByType(StudentExtension::class.java)
        studentExtension.apply {
            nickname = "小猪"
            age = 160
        }

    }
}