import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

val kotlinVersion = "1.3.71"
val bintrayVersion = "1.8.4"
val dependencyUpdatesVersion = "0.28.0"

repositories {
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("com.jfrog.bintray.gradle:gradle-bintray-plugin:$bintrayVersion")
    implementation("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
    implementation("com.github.ben-manes:gradle-versions-plugin:$dependencyUpdatesVersion")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

tasks {
    withType<KotlinCompile> { kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString() }
}
