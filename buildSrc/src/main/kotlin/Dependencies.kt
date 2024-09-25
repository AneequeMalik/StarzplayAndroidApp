import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
    const val INTUIT_SDP = "com.intuit.sdp:sdp-android:${Versions.INTUIT}"
    const val INTUIT_SSP = "com.intuit.ssp:ssp-android:${Versions.INTUIT}"

    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.HTTP}"
    const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.HTTP}"
    const val OKHTTP_CONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val MOSHI_CONVERTER = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT}"
    const val MOSHI = "com.squareup.moshi:moshi:${Versions.MOSHI}"
    const val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:${Versions.MOSHI_KOTLIN}"
    const val MOSHI_ADAPTERS = "com.squareup.moshi:moshi-adapters:${Versions.MOSHI_ADAPTERS}"

    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"

    const val ROOM_DB = "androidx.room:room-runtime:${Versions.ROOM_DB}"
    const val ROOM_DB_KAPT = "androidx.room:room-compiler:${Versions.ROOM_DB}"
    const val ROOM_DB_KTX = "androidx.room:room-ktx:${Versions.ROOM_DB}"

    const val EXOPLAYER = "androidx.media3:media3-exoplayer:${Versions.EXOPLAYER}"
    const val EXOPLAYER_DASH = "androidx.media3:media3-exoplayer-dash:${Versions.EXOPLAYER}"
    const val EXOPLAYER_MEDIA_UI = "androidx.media3:media3-ui:${Versions.EXOPLAYER}"
}

fun DependencyHandler.domain() {
    implementation(project(":domain"))
}

fun DependencyHandler.intuit() {
    implementation(Dependencies.INTUIT_SDP)
    implementation(Dependencies.INTUIT_SSP)
}

fun DependencyHandler.network() {
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.OKHTTP_LOGGING_INTERCEPTOR)
    implementation(Dependencies.OKHTTP_CONVERTER)
    implementation(Dependencies.MOSHI_CONVERTER)
    implementation(Dependencies.MOSHI)
    implementation(Dependencies.MOSHI_KOTLIN)
    implementation(Dependencies.MOSHI_ADAPTERS)
}

fun DependencyHandler.glide() {
    implementation(Dependencies.GLIDE)
}

fun DependencyHandler.roomDB() {
    implementation(Dependencies.ROOM_DB)
    kapt(Dependencies.ROOM_DB_KAPT)
    implementation(Dependencies.ROOM_DB_KTX)
}

fun DependencyHandler.exoplayer() {
    implementation(Dependencies.EXOPLAYER)
    implementation(Dependencies.EXOPLAYER_DASH)
    implementation(Dependencies.EXOPLAYER_MEDIA_UI)
}