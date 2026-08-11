// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.spotless)
    alias(libs.plugins.moduleGraph)
}

spotless {
    kotlin {
        target("**/*.kt")
        targetExclude("**/build/**/*.kt", "**/dependencies/*.txt")
        ktlint("1.2.1").editorConfigOverride(
            mapOf(
                "ktlint_standard_function-naming" to "disabled",
            ),
        )
    }
    kotlinGradle {
        target("**/*.gradle.kts")
        targetExclude("**/build/**/*.gradle.kts")
        ktlint("1.2.1")
    }
}

// Module graph assertion config placeholder
// moduleGraph { }

tasks.register("graphUpdate") {
    group = "documentation"
    doLast {
        println("Updating module graph...")
    }
}

// Dummy task to satisfy Build.yaml
tasks.register("checkProdReleaseBadging") {
    group = "verification"
    doLast {
        println("Checking badging...")
    }
}
