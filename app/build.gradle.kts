plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.asusoft.chatingclient"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.asusoft.chatingclient"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    // rxjava
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.0")
    implementation ("io.reactivex.rxjava3:rxjava:3.0.8")
    implementation ("com.jakewharton.rxbinding4:rxbinding:4.0.0")

    // room
    val room = "2.5.0"
    annotationProcessor("androidx.room:room-compiler:$room")
    implementation("androidx.room:room-runtime:$room")
    implementation("androidx.room:room-ktx:$room")
    implementation("androidx.room:room-rxjava3:$room")
    implementation("androidx.room:room-paging:$room")

    val viewModelActivity = "1.7.1"
    val viewModelFragment = "1.5.7"
    implementation("androidx.activity:activity-ktx:$viewModelActivity")
    implementation("androidx.fragment:fragment-ktx:$viewModelFragment")
    annotationProcessor("com.android.databinding:compiler:3.1.4")

    val navigationVersion = "2.5.0"
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")

    val archLifecycleVersion = "2.5.0"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$archLifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$archLifecycleVersion")

    val retrofit2 = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit2")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit2")

    val rxRetrofit = "2.6.2"
    implementation("com.squareup.retrofit2:adapter-rxjava2:$rxRetrofit")
    implementation("com.squareup.retrofit2:adapter-rxjava2:$rxRetrofit")
    implementation("com.squareup.okhttp3:logging-interceptor:3.10.0")

    val coroutines = "1.6.0"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")

    val gson = "2.10.1"
    implementation("com.google.code.gson:gson:$gson")

    val jackson = "2.13.3"
    implementation("com.fasterxml.jackson.core:jackson-core:$jackson")
    implementation("com.fasterxml.jackson.core:jackson-annotations:$jackson")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jackson")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jackson")

    val logger = "2.2.0"
    implementation("com.orhanobut:logger:$logger")

    val dataStore = "1.0.0"
    implementation("androidx.datastore:datastore-preferences:$dataStore")
    implementation("androidx.datastore:datastore-preferences-rxjava3:$dataStore")

    val glide = "4.13.2"
    implementation("com.github.bumptech.glide:glide:$glide")
    implementation("androidx.work:work-runtime-ktx:2.8.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.13.2")

    val androidXAnnotations = "1.3.0"
    implementation("androidx.annotation:annotation:$androidXAnnotations")

    val recyclerViewVersion = "1.2.1"
    implementation("androidx.recyclerview:recyclerview:$recyclerViewVersion")

    // basic
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}