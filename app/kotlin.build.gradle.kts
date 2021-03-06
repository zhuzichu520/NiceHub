plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("TestPlugin")
}

Config.initJenkinsProperties(project)

project.android {

    compileSdkVersion(Config.compileSdkVersion())

    signingConfigs {
        create("appSign") {
            keyAlias = Config.keyAlias()
            keyPassword = Config.keyPassword()
            storeFile = file(Config.storeFile())
            storePassword = Config.storePassword()
        }
    }

    defaultConfig {
        applicationId = Config.applicationId()
        minSdkVersion(Config.minSdkVersion())
        targetSdkVersion(Config.targetSdkVersion())
        versionCode = Config.versionCode()
        versionName = Config.versionName()
        signingConfig = signingConfigs.getByName("appSign")
        renderscriptTargetApi = 18
        renderscriptSupportModeEnabled = true
        resValue("string", "app_name_new", Config.appName())
        val fields = Config.getBuildConfigFields()
        fields.forEach {
            buildConfigField(it[0], it[1], it[2])
        }
    }

    buildTypes {

        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            isZipAlignEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {

        }
    }

    flavorDimensions("channel")
    productFlavors {
        create("GooglePlay") {
            dimension = "channel"
            applicationIdSuffix = ".google"
            manifestPlaceholders.apply {
                put("CHANNEL_NAME", name)
                put("ic_launcher_new", "@mipmap/ic_launcher_google")
                put("ic_launcher_round_new", "@mipmap/ic_launcher_google_round")
            }
        }
        create("HuaWei") {
            dimension = "channel"
            applicationIdSuffix = ".huawei"
            manifestPlaceholders.apply {
                put("CHANNEL_NAME", name)
                put("ic_launcher_new", "@mipmap/ic_launcher_huawei")
                put("ic_launcher_round_new", "@mipmap/ic_launcher_huawei_round")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    dataBinding {
        isEnabled = true
    }

    androidExtensions {
        isExperimental = true
    }

    sourceSets {
        sourceSets["main"].java.srcDir("src/main/kotlin")
    }

}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to "*.jar"))
    api(project(path = ":shared"))
    kapt(Dep.kaptRoomCompiler)
    kapt(Dep.kaptDaggerCompiler)
    kapt(Dep.kaptDaggerProcessor)
    kapt(Dep.kaptGlide)
    kapt(Dep.androidxDatabinding)
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.0-beta-3")
    debugImplementation("com.didichuxing.doraemonkit:doraemonkit:2.0.1")
}

student {
    nickname = "小猪"
    age = 160
}