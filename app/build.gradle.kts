plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 31

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"http://www.omdbapi.com\"")
            buildConfigField("String", "API_KEY", getLocalProperty("movie.key"))
        }

        create("develop") {
            initWith(getByName("debug"))
        }
    }

    defaultConfig {
        applicationId = "com.alext.movie"
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Libraries.coreKtx)
    implementation(Libraries.appCompat)
    implementation(Libraries.googleMaterial)

    implementation(Libraries.hiltAndroid)
    kapt(Libraries.hiltAndroidCompiler)

    implementation(Libraries.navFragmentKtx)
    implementation(Libraries.navUIKtx)

    implementation(Libraries.sdpAndroid)

    implementation(Libraries.okHttp3)
    implementation(Libraries.okHttp3Logging)
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitGsonConverter)
    implementation(Libraries.gson)

    implementation(Libraries.glide)
    annotationProcessor(Libraries.glideAnnotationProcessor)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}