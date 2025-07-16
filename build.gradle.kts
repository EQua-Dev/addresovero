import org.gradle.api.publish.maven.MavenPublication


plugins {
    id("com.android.library") version "8.11.1"
    id("org.jetbrains.kotlin.android") version "1.9.0"
    id("kotlin-parcelize")
    id("maven-publish")
}

android {
    namespace = "tech.sourceid.addressverification"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
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

    kotlinOptions {
        jvmTarget = "1.8"
    }

    // Add publishing configuration for Android libraries
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

// Publishing configuration - afterEvaluate is necessary for Android libraries
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.EQua-Dev"
                artifactId = "sid-addressverification"
                version = "1.0.2"

                pom {
                    name.set("SIDAddressVerification")
                    description.set("A SourceID native Android library for verifying addresses using Google Places API and Location Services.")
                    url.set("https://github.com/EQua-Dev/sid-addressverification")

                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }

                    developers {
                        developer {
                            id.set("EQua-Dev")
                            name.set("EQua Developer")
                            email.set("richard@sourceid.tech")
                        }
                    }

                    scm {
                        connection.set("scm:git:git://github.com/EQua-Dev/sid-addressverification.git")
                        developerConnection.set("scm:git:ssh://github.com/EQua-Dev/sid-addressverification.git")
                        url.set("https://github.com/EQua-Dev/sid-addressverification")
                    }
                }
            }
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    // Compose BOM for version alignment
    implementation(platform("androidx.compose:compose-bom:2024.02.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.9.0")

    // Debug dependencies
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Google Places
    implementation("com.google.android.libraries.places:places:3.3.0")

    // Location Services
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")

    // HTTP client
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
}