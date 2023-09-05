@file:Suppress("UnstableApiUsage")

import java.util.*

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.secrets.gradle.plugin)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.uos.cokcok.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "GOOGLE_BASE_URL", properties["GOOGLE_BASE_URL"] as String)
    }


    signingConfigs {
        create("release") {
            val propertiesFile = rootProject.file("local.properties")
            val properties = Properties()
//            properties.load(propertiesFile.inputStream())
//            storeFile = file(properties["STORE_FILE"] as String)
//            storePassword = properties["STORE_PASSWORD"] as String
//            keyAlias = properties["KEY_ALIAS"] as String
//            keyPassword = properties["KEY_PASSWORD"] as String
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false

            buildConfigField("String", "SERVICE_URL", properties["DEBUG_BASE_URL"] as String)
        }

        release {
            isMinifyEnabled = true
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )

            buildConfigField("String", "SERVICE_URL", properties["RELEASE_BASE_URL"] as String)
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true

        // kotest
        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.kotlin.serialization.json)

    // coroutine
    implementation(libs.bundles.coroutine)

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // retrofit, okhttp
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.okhttp)

    implementation(libs.paging.runtime)
    implementation(libs.datastore)
    
    // logger
    implementation(libs.logger)

    testImplementation(libs.bundles.test)
}