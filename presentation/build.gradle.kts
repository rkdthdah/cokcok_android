@file:Suppress("UnstableApiUsage")

import com.android.build.api.dsl.JacocoOptions
import com.android.build.api.dsl.TestCoverage
import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.google.service)
    alias(libs.plugins.secrets.gradle.plugin)
    alias(libs.plugins.navigation.safeargs)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.uos.cokcok.presentation"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "POLICY_URL", properties["POLICY_URL"] as String)

        val propertiesFile = rootProject.file("local.properties")
        val localProperties = Properties()
        localProperties.load(propertiesFile.inputStream())
        buildConfigField("String", "KAKAO_NATIVE_APP_KEY", localProperties["KAKAO_NATIVE_APP_KEY"] as String)
        manifestPlaceholders["KAKAO_NATIVE_APP_KEY"] = localProperties["KAKAO_NATIVE_APP_KEY_NO_QUOTES"] as String
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        dataBinding = true
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

    implementation(libs.core)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)

    implementation(libs.bundles.navigation)
    implementation(libs.bundles.lifecycle)
    implementation(libs.activity)
    implementation(libs.fragment)
    implementation(libs.recyclerview)
    implementation(libs.paging.runtime)

    // coroutines
    implementation(libs.bundles.coroutine)

    // hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.fragment)
    kapt(libs.hilt.compiler)


    // glide
    implementation(libs.glide)
    kapt(libs.glide.compiler)

    // splash
    implementation(libs.splash)

    // lottie
    implementation(libs.lottie)

    // auth - google, kakao, naver
    implementation(libs.google.auth)
    implementation(libs.kakao.auth)
    implementation(libs.kakao.share)
    // logger
    implementation(libs.logger)

    // firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging)

    // test
    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.android.test.espresso)
}