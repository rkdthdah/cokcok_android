@file:Suppress("UnstableApiUsage")

import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.google.service)
    alias(libs.plugins.hilt)
    alias(libs.plugins.secrets.gradle.plugin)
    alias(libs.plugins.navigation.safeargs)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.uos.cokcok"
    compileSdk = 33

    val propertiesFile = rootProject.file("local.properties")
    val localProperties = Properties()
    localProperties.load(propertiesFile.inputStream())

    defaultConfig {
        targetSdk = 33
        minSdk = 26
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "GOOGLE_BASE_URL", properties["GOOGLE_BASE_URL"] as String)
        buildConfigField("String", "POLICY_URL", properties["POLICY_URL"] as String)
        buildConfigField("String", "KAKAO_NATIVE_APP_KEY", localProperties["KAKAO_NATIVE_APP_KEY"] as String)
    }


    signingConfigs {
        create("release") {
//            storeFile = file(localProperties["STORE_FILE"] as String)
//            storePassword = localProperties["STORE_PASSWORD"] as String
//            keyAlias = localProperties["KEY_ALIAS"] as String
//            keyPassword = localProperties["KEY_PASSWORD"] as String
        }
    }

    buildTypes {
        debug {
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

    buildFeatures {
        dataBinding = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true

        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }
}

dependencies {
    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.startup)
    implementation(libs.logger)

    implementation(libs.kakao.auth)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)

    testImplementation(libs.bundles.test)
}