import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import tasks.setup

plugins {
    id(Plugins.detekt) version Versions.detekt
    jacoco
}

apply(plugin = Plugins.gradleVersions)

allprojects {
    tasks {
        withType(KotlinCompile::setup)
    }
}

dependencies {
    detektPlugins(Dependencies.detektFormatting)
}

detekt {
    toolVersion = Versions.detekt
    ignoreFailures = true
    autoCorrect = true
}

tasks {
    withType<Test> {
        // ToFix MockWebServer with gradle parallel tests execution
        // maxParallelForks = Runtime.getRuntime().availableProcessors()
        useJUnitPlatform()
        useTestNG()
    }

    withType(DependencyUpdatesTask::setup)
}
