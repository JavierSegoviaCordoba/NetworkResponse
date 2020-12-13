package tasks

import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.TaskContainerScope
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val TaskContainerScope.baseKotlinOptions: Unit
    get() {
        withType<KotlinCompile>().all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }
    }
