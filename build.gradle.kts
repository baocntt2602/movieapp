// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath(ClassPaths.hiltAndroidPlugin)
        classpath(ClassPaths.kotlinGradlePlugin)
        classpath(ClassPaths.navigationSafeArgsPlugin)
    }
}

plugins {
    id ("com.android.application").apply {
        version("7.1.1")
        apply(false)
    }
    id ("com.android.library").apply {
        version("7.1.1")
        apply(false)
    }
    id ("org.jetbrains.kotlin.android").apply {
        version("1.6.10")
        apply(false)
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}