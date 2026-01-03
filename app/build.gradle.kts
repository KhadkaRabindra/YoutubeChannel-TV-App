plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.borkor.shobizandoid"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.borkor.shobizandoid"
        minSdk = 24
        targetSdk = 36
        versionCode = 8
        versionName = "1.7"
        vectorDrawables {
            useSupportLibrary = true
        }

    }

    buildTypes {
        debug {
            buildConfigField(
                type = "String",
                name = "YOUTUBE_BASE_URL",
                value = "\"https://www.googleapis.com/\""
            )
            buildConfigField(
                type = "String",
                name = "YOUTUBE_API_KEY",
                value = "\"AIzaSyDtThfaOPo-uaG3n2RCibzR82C35cer6o4\""
            )
            buildConfigField(
                type = "String",
                name = "YOUTUBE_CHANNEL_ID",
                value = "\"UC-9-kyTW8ZkZNDHQJ6FgpwQ\""
            )
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            buildConfigField(
                type = "String",
                name = "YOUTUBE_BASE_URL",
                value = "\"https://youtube.googleapis.com/\""
            )
            buildConfigField(
                type = "String",
                name = "YOUTUBE_API_KEY",
                value = "\"AIzaSyAT71zsKZFukMoMapbnNM-bTTRjRF1ucAg\""
            )
            buildConfigField(
                type = "String",
                name = "YOUTUBE_CHANNEL_ID",
                value = "\"UCCFdkJZWZrvkfqQVtkvYnqA\""
            )
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
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
        }
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kapt {
        correctErrorTypes = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("androidx.compose.material3:material3")
    implementation(platform("androidx.compose:compose-bom:2025.12.01"))
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.tv:tv-foundation:1.0.0-alpha12")
    implementation("androidx.tv:tv-material:1.1.0-alpha01")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.10.0")
    implementation("androidx.activity:activity-compose:1.12.2")
    implementation("com.google.accompanist:accompanist-webview:0.36.0")
    implementation("com.google.android.exoplayer:exoplayer:2.19.1")
    implementation("androidx.navigation:navigation-compose:2.9.6")


    // Gson
    implementation("com.google.code.gson:gson:2.13.2")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation("com.squareup.okhttp3:okhttp:5.3.2")
    //logging
    implementation("com.squareup.okhttp3:logging-interceptor:5.3.2")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.10.0")
    implementation("androidx.activity:activity-ktx:1.12.2")

    //dagger-hilt
    implementation("com.google.dagger:hilt-android:2.57.2")
    kapt("com.google.dagger:hilt-android-compiler:2.57.2")

    implementation("androidx.room:room-ktx:2.8.4")
    implementation("com.google.firebase:firebase-firestore-ktx:25.1.4")

    //coil
    implementation("io.coil-kt:coil-compose:2.7.0")

    // Kotlin Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

    //paging
    implementation ("androidx.paging:paging-compose:3.3.6")
    implementation ("androidx.paging:paging-runtime-ktx:3.3.6")
    implementation("androidx.paging:paging-common-ktx:3.3.6")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.10.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.10.0")
    implementation ("androidx.activity:activity-ktx:1.12.2")
    implementation ("androidx.fragment:fragment-ktx:1.8.9")

    implementation ("androidx.activity:activity-compose:1.12.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.10.0")

    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:13.0.0")

    implementation(platform("com.google.firebase:firebase-bom:34.7.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-database")
    implementation("com.google.firebase:firebase-storage")

    implementation ("com.google.android.gms:play-services-ads:24.9.0")
    implementation ("com.parse:parse-android:1.17.3")
    implementation("com.squareup.okhttp3:logging-interceptor:5.3.2")

    androidTestImplementation(platform("androidx.compose:compose-bom:2025.12.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

}
