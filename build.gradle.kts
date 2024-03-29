import com.jfrog.bintray.gradle.BintrayExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.4"
    id("com.gradle.build-scan") version "2.3"
    kotlin("jvm") version "1.3.50"
}

group = "net.besttoolbars"
version = "0.4.2"

repositories {
    jcenter()
    maven { url = uri("https://dl.bintray.com/besttoolbars/repo") }
}


val junit = "5.4.0"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    api("net.besttoolbars:affiliate-connector-core:0.0.4")

    testImplementation("org.mockito:mockito-junit-jupiter:2.25.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junit")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junit")
}

tasks {
    javadoc {
        if (JavaVersion.current().isJava9Compatible) {
            (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
        }
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    withType(JavaCompile::class) {
        options.compilerArgs.add("-parameters")
    }

    named<Test>("test") {
        useJUnitPlatform()
    }

    register<Jar>("sourcesJar") {
        from(sourceSets.main.get().allSource)
        archiveClassifier.set("sources")
    }

    register<Jar>("javadocJar") {
        from(javadoc)
        archiveClassifier.set("javadoc")
    }
}

publishing {
    publications {
        create<MavenPublication>("gdeslon") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])
        }
    }
}


bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    publish = true
    override = true
    setPublications("gdeslon")
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "repo"
        name = project.name
        userOrg = "besttoolbars"
        websiteUrl = "https://github.com/Besttoolbars/$name"
        githubRepo = "besttoolbars/$name"
        vcsUrl = "https://github.com/Besttoolbars/$name"
        description = "Gdeslon jvm connector"
        setLabels("kotlin", "java", "gdeslon")
        setLicenses("Apache-2.0")
    })
}

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
}
