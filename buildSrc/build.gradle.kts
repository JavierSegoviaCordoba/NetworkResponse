plugins {
    `kotlin-dsl`
}

val kotlin = "1.4-M2"
val dependencyUpdates = "0.28.0"
val detekt = "1.9.1"
val nexus = "0.21.2"
val dokka = "0.10.1"

repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://plugins.gradle.org/m2")
    jcenter()
    mavenCentral()
    google()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin")
    implementation("org.jetbrains.kotlin:kotlin-serialization:$kotlin")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detekt")
    implementation("com.github.ben-manes:gradle-versions-plugin:$dependencyUpdates")
    implementation("io.codearte.gradle.nexus:gradle-nexus-staging-plugin:$nexus")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:$dokka")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
