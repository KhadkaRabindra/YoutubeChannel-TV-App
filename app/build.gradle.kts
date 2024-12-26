plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.borkor.shobizandoid"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.borkor.shobizandoid"
        minSdk = 21
        targetSdk = 35
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
                value = "\"<YOUR_YOUTUBE_API_KEY>\""
            )
            buildConfigField(
                type = "String",
                name = "YOUTUBE_CHANNEL_ID",
                value = "\"<YOUR_CHANNEL_ID>\""
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
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
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.compose.material3:material3")
    implementation(platform("androidx.compose:compose-bom:2024.12.01"))
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.tv:tv-foundation:1.0.0-alpha11")
    implementation("androidx.tv:tv-material:1.0.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("com.google.accompanist:accompanist-webview:0.28.0")
    implementation("com.google.android.exoplayer:exoplayer:2.19.1")
    implementation("androidx.navigation:navigation-compose:2.8.5")


    // Gson
    implementation("com.google.code.gson:gson:2.11.0")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.9")
    //logging
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-ktx:1.9.3")

    //dagger-hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    implementation("androidx.room:room-ktx:2.6.1")
    implementation("com.google.firebase:firebase-firestore-ktx:25.1.1")

    //coil
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Kotlin Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    //paging
    implementation ("androidx.paging:paging-compose:3.3.5")
    implementation ("androidx.paging:paging-runtime-ktx:3.3.5")
    implementation("androidx.paging:paging-common-ktx:3.3.5")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation ("androidx.activity:activity-ktx:1.9.3")
    implementation ("androidx.fragment:fragment-ktx:1.8.5")

    implementation ("androidx.activity:activity-compose:1.9.3")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")

    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.1")

    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-database")
    implementation("com.google.firebase:firebase-storage")

    implementation ("com.google.android.gms:play-services-ads:23.6.0")
    implementation ("com.parse:parse-android:1.17.3")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    androidTestImplementation(platform("androidx.compose:compose-bom:2024.12.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

}
