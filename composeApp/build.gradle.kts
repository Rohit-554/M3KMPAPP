import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {

    // SPM SUPPORT
    kotlin {
        swiftPMDependencies {
            swiftPackage(
                url = url("https://github.com/firebase/firebase-ios-sdk.git"),
                version = from("12.11.0"),
                products = listOf(
                    product("FirebaseAnalytics"),
                    product("FirebaseAuth")
                )
            )
        }
    }

    compilerOptions {
        freeCompilerArgs.add("-Xexplicit-backing-fields")
        freeCompilerArgs.add("-Xcontext-parameters")
        freeCompilerArgs.add("-Xannotation-target-all")
        freeCompilerArgs.add("-Xexplicit-context-arguments")
        freeCompilerArgs.add("-Xcollection-literals")
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm("desktop")

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.koin.core)
            }
        }
        iosMain.dependencies {
        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.androidx.lifecycle.viewmodel)

            implementation(libs.material.icons.extended)
            // Koin
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            // Ktor Client

            // Room 3.0

            // Navigation3 (navigation3-runtime is bundled in navigation3-ui)
            implementation(libs.navigation3.ui)

            // Kotlin Serialization
            implementation(libs.kotlinx.serialization.json)

            // Coil 3
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)

            // kotlinx-datetime
            implementation(libs.kotlinx.datetime)

            // multiplatform-settings

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.turbine)
            implementation(libs.kotest.assertions)
            implementation(libs.mockative)
        }
    }

    android {
        namespace = "io.jadu.m3App"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        androidResources {
            enable = true
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)

    // Room KSP processors

    // Mockative KSP processor
    add("kspCommonMainMetadata", libs.mockative.processor)
    add("kspAndroid", libs.mockative.processor)
    add("kspIosArm64", libs.mockative.processor)
    add("kspIosSimulatorArm64", libs.mockative.processor)
}

// Room schema directory configured via KSP arguments
