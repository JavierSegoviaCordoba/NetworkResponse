package tasks

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

fun DependencyUpdatesTask.setup() = rejectVersionIf { candidate.version.isNonStable }

private val String.isNonStable: Boolean
    get() = this.contains("alpha", true) || this.contains("beta", true)
