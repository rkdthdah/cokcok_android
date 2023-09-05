plugins {
    `java-library`
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.coroutine.core)
    implementation(libs.paging.common)
    implementation(libs.logger)
    implementation(libs.javax.inject)

    testImplementation(libs.bundles.test)
}