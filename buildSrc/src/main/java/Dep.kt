object Dep {
    object Version {
        const val kotlin = "1.3.50"
        const val buildGradle = "3.5.1"
        const val androidxCore = "1.1.0"
        const val androidxKtx = "1.1.0"
        const val androidxAnnotation = "1.1.0"
        const val androidxConstraintlayout = "2.0.0-beta2"
        const val androidxNavigation = "2.2.0-beta01"
        const val dagger = "2.24"
        const val material = "1.1.0-beta01"
        const val retrofit = "2.4.0"
        const val okhttp = "3.10.0"
        const val rxjava = "2.2.12"
        const val rxandroid = "2.1.1"
        const val rxbinding = "3.0.0"
        const val autodispose = "1.4.0"
        const val adapter = "3.1.1"
        const val glide = "4.10.0"
        const val timber = "4.7.1"
        const val swiperefreshlayout = "1..0-alpha03"
        const val libs = "1.0.21"
        const val mvvm = "1.0.22"
        const val widget = "1.0.23"
    }

    const val swiperefreshlayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swiperefreshlayout}"

    const val pluginBuildGradle =
        "com.android.tools.build:gradle:${Version.buildGradle}"

    const val pluginkotlinGradle =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"

    const val androidxDatabinding =
        "androidx.databinding:databinding-compiler:${Version.buildGradle}"

    const val androidxCore =
        "androidx.appcompat:appcompat:${Version.androidxCore}"

    const val androidxKtx =
        "androidx.core:core-ktx:${Version.androidxKtx}"

    const val androidxAnnotation =
        "androidx.annotation:annotation:${Version.androidxAnnotation}"

    const val androidxConstraintlayout =
        "androidx.constraintlayout:constraintlayout:${Version.androidxConstraintlayout}"

    const val androidxNavigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Version.androidxNavigation}"

    const val androidxNavigationUi =
        "androidx.navigation:navigation-ui-ktx:${Version.androidxNavigation}"

    const val widget =
        "com.github.zhuzichu520:widget:${Version.widget}"

    const val libs =
        "com.github.zhuzichu520:libs:${Version.libs}"

    const val mvvm =
        "com.github.zhuzichu520:mvvm:${Version.mvvm}"

    const val daggerAndroid =
        "com.google.dagger:dagger-android:${Version.dagger}"

    const val daggerAndroidSupport =
        "com.google.dagger:dagger-android-support:${Version.dagger}"

    const val kotlinStadlibJdk8 =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Version.kotlin}"

    const val material =
        "com.google.android.material:material:${Version.material}"

    const val okhttp =
        "com.squareup.okhttp3:okhttp:${Version.okhttp}"

    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"

    const val retrofit =
        "com.squareup.retrofit2:retrofit:${Version.retrofit}"

    const val retrofitGsonConverter = "" +
            "com.squareup.retrofit2:converter-gson:${Version.retrofit}"

    const val retrofitAdapter =
        "com.squareup.retrofit2:adapter-rxjava2:${Version.retrofit}"

    const val retrofitScalarsConverter =
        "com.squareup.retrofit2:converter-scalars:${Version.retrofit}"

    const val rxjava2 =
        "io.reactivex.rxjava2:rxjava:${Version.rxjava}"

    const val rxandroid =
        "io.reactivex.rxjava2:rxandroid:${Version.rxandroid}"

    const val rxbinding =
        "com.jakewharton.rxbinding3:rxbinding-core:${Version.rxbinding}"

    const val autodispose =
        "com.uber.autodispose:autodispose:${Version.autodispose}"

    const val autodisposeAndroid =
        "com.uber.autodispose:autodispose-android:${Version.autodispose}"

    const val autodisposeArchcomponents =
        "com.uber.autodispose:autodispose-android-archcomponents:${Version.autodispose}"

    const val adapter =
        "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter:${Version.adapter}"

    const val adapterRecyclerview =
        "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-recyclerview:${Version.adapter}"

    const val kaptDaggerCompiler =
        "com.google.dagger:dagger-compiler:${Version.dagger}"

    const val kaptDaggerProcessor =
        "com.google.dagger:dagger-android-processor:${Version.dagger}"

    const val glideOkhttpIntegration =
        "com.github.bumptech.glide:okhttp3-integration:${Version.glide}"

    const val glide =
        "com.github.bumptech.glide:glide:${Version.glide}"

    const val kaptGlide =
        "com.github.bumptech.glide:compiler:${Version.glide}"

    const val timber =
        "com.jakewharton.timber:timber:${Version.timber}"
}