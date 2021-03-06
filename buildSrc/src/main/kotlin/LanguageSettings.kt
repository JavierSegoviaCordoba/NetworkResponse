import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

val NamedDomainObjectContainer<KotlinSourceSet>.defaultLanguageSettings: Unit
    get() {
        all {
            with(languageSettings) {
                useExperimentalAnnotation("kotlin.ExperimentalUnsignedTypes")
                useExperimentalAnnotation("io.ktor.util.KtorExperimentalAPI")
                useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
                useExperimentalAnnotation("kotlinx.coroutines.FlowPreview")
                useExperimentalAnnotation("kotlinx.coroutines.ObsoleteCoroutinesApi")
                useExperimentalAnnotation("kotlinx.serialization.ExperimentalSerializationApi")
                useExperimentalAnnotation("kotlinx.serialization.InternalSerializationApi")
                useExperimentalAnnotation("kotlin.RequiresOptIn")
                useExperimentalAnnotation("kotlin.time.ExperimentalTime")
                useExperimentalAnnotation("kotlinx.serialization.ExperimentalSerializationApi")
            }
        }
    }
