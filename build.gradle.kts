plugins {
    id("com.android.library") version "8.2.0"
    id("org.jetbrains.kotlin.android") version "1.9.22"
    id("maven-publish")
}

android {
    // android 블록은 그대로 유지
    namespace = "com.leom.game_tutorial_ui"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.foundation:foundation")

    // libs 참조를 직접 버전으로 변경
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")

//    implementation(project(":commonDatas"))
    implementation("com.github.LetsWorkAround:commonDatas:v1.0.7")  // 최신 release 버전

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.compose.runtime:runtime:1.5.4")
}

afterEvaluate {
    // publishing 블록은 그대로 유지
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])

                groupId = "com.github.LetsWorkAround"
                artifactId = "leom-game-tutorial-ui"
                version = "v1.0.6"

                pom {
                    name.set("leom-game-tutorial-ui")
                    description.set("Game Tutorial UI Library")
                }
            }
        }
    }
}