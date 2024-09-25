plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.aneeque.domain"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField(
            type = "String",
            name = "TMDB_BASE_URL",
            value = "\"https://api.themoviedb.org\""
        )

        buildConfigField(
            type = "String",
            name = "API_KEY",
            value = "\"6d30027cb09e1642aa2cc668111b86a6\""
        )
    }

    buildTypes {
        debug {
            isMinifyEnabled = false

        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.lifecycle.livedata.core.ktx)
    network()
    roomDB()
}
