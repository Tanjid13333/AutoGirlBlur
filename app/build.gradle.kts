plugins { id("com.android.application"); id("org.jetbrains.kotlin.android") }

android { namespace = "com.autogirlblur"; compileSdk = 33
    defaultConfig { applicationId = "com.autogirlblur"; minSdk = 26; targetSdk = 33; versionCode = 1; versionName = "0.1.2" }
    compileOptions { sourceCompatibility = JavaVersion.VERSION_11; targetCompatibility = JavaVersion.VERSION_11 }
    kotlinOptions { jvmTarget = "11" }
}

dependencies {
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.lifecycle:lifecycle-service:2.8.7")
    implementation("com.google.mlkit:face-detection:16.1.7")
}
