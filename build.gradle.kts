plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.devtools.ksp") version "1.9.25-1.0.20" apply false
}

buildscript {
    repositories {
        google()
    }
    dependencies {
        val nav_version = "2.9.3"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}