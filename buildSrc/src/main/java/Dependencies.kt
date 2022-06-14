object Versions {
    const val hiltVersion = "2.38.1"

    const val kotlinVersion = "1.4.31"

    const val coreKtxVersion = "1.7.0"
    const val appCompatVersion = "1.4.1"
    const val googleMaterialVersion = "1.5.0"

    const val navVersion = "2.4.1"
    const val sdpAndroidVersion = "1.0.6"

    const val okhttp3Version = "4.9.3"
    const val retrofitVersion = "2.9.0"
    const val gsonVersion = "2.8.7"

    const val glideVersion = "4.13.0"
    const val rvVersion = "1.2.0-alpha04"
}

object Libraries {

    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val hiltAndroidCompiler =
        "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val googleMaterial =
        "com.google.android.material:material:${Versions.googleMaterialVersion}"

    const val navFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
    const val navUIKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"

    const val sdpAndroid = "com.intuit.sdp:sdp-android:${Versions.sdpAndroidVersion}"
    const val sspAndroid = "com.intuit.ssp:ssp-android:${Versions.sdpAndroidVersion}"
    const val okHttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp3Version}"
    const val okHttp3Logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3Version}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitGsonConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideAnnotationProcessor =
        "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.rvVersion}"
}

object ClassPaths {
    const val hiltAndroidPlugin =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val navigationSafeArgsPlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navVersion}"
}
