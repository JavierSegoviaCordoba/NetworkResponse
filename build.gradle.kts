import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import tasks.setup

plugins {
    id(Plugins.gradleVersions) version Versions.gradleVersions
    id(Plugins.detekt) version Versions.detekt
    jacoco
}

subprojects {
    tasks {
        withType<Test> {
            // ToFix MockWebServer with gradle parallel tests execution
            // maxParallelForks = Runtime.getRuntime().availableProcessors()
            useJUnitPlatform()
            useTestNG()
        }
    }
}

allprojects {
    repositories {
        jcenter()
    }

    apply(plugin = Plugins.detekt)

    dependencies {
        detektPlugins(Dependencies.detektFormatting)
    }

    detekt {
        toolVersion = Versions.detekt
        ignoreFailures = true
        autoCorrect = true
    }

    tasks {
        withType(KotlinCompile::setup)
    }
}
