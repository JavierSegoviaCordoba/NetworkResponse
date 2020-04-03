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