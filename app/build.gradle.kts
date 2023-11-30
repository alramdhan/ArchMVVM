import com.android.build.gradle.internal.cxx.configure.isError
import com.android.ide.common.repository.main

plugins {
    id("com.android.application")
}
val compileVersion by extra(34)
val targetVersion by extra(33)

android {
    namespace = "id.wikrama.archmvvm"
    compileSdk = compileVersion

    defaultConfig {
        applicationId = "id.wikrama.archmvvm"
        minSdk = 23
        targetSdk = targetVersion
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    dataBinding {
        enable = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildToolsVersion = "33.0.1"
}

dependencies {
    val lifeVersion = "2.2.0"
    val retrofitVersion = "2.9.0"
    val gsonVersion = "2.10.1"

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.lifecycle:lifecycle-extensions:$lifeVersion")
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}