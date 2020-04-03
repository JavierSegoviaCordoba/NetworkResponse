package plugins.extensions

import org.gradle.api.Project
import java.util.Properties

internal val Project.localProperties
    get() = Properties().apply { load(rootProject.file("local.properties").inputStream()) }
