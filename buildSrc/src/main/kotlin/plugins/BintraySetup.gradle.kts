import plugins.extensions.bintray
import plugins.extensions.publishing

apply(plugin = Plugins.bintray)
apply(plugin = Plugins.mavenPublish)

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from("src/main/java")
}

bintray {
    runCatching {
        user = property("user") as String?
        key = property("key") as String?
    }
    publish = true
    pkg.apply {
        repo = Bintray.repo
        name = Bintray.name
        userOrg = Bintray.userOrg
        description = Bintray.description
        websiteUrl = Bintray.websiteUrl
        setLicenses(Bintray.licenses)
        issueTrackerUrl = Bintray.issueTrackerUrl
        vcsUrl = Bintray.vscUrl
        version.apply { name = Build.version }
        setLabels(Bintray.label1, Bintray.label2, Bintray.label3, Bintray.label4)
    }
    setPublications(Bintray.name)
}

publishing {
    publications {
        create<MavenPublication>(Bintray.name) {
            groupId = Build.groupId
            artifactId = Build.artifactId
            version = Build.version
            artifact(sourcesJar)
            artifact(Bintray.artifactDir)
        }
    }
}
