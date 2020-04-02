import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import tasks.setup

plugins {
    id(Plugins.versions) version Versions.versions
    id(Plugins.detekt) version Versions.detekt
}

subprojects {
    tasks {
        withType<Test> { maxParallelForks = Runtime.getRuntime().availableProcessors() }
    }
}

allprojects {
    apply(plugin = Plugins.detekt)

    detekt {
        toolVersion = Versions.detekt
        ignoreFailures = true
    }

    tasks {
        withType(KotlinCompile::setup)
    }
}
