import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("maven-publish")
    id("java-library")
    id("com.gradleup.shadow") version "9.3.2"
}

group = "me.astralisvox"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://libraries.minecraft.net/")
    maven("https://github.com/deanveloper/SkullCreator/raw/mvn-repo/")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://repo.maven.apache.org/maven2/")
}

dependencies {
    implementation("com.tchristofferson:ConfigUpdater:1.1-SNAPSHOT")
    implementation("net.kyori:adventure-api:4.12.0")
    implementation("net.kyori:adventure-text-serializer-legacy:4.12.0")
    compileOnly("dev.dbassett:skullcreator:3.0.1")
    compileOnly("org.spigotmc:spigot-api:1.21.11-R0.2-SNAPSHOT")
    compileOnly("org.projectlombok:lombok:1.18.24")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(22))
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.isIncremental = true
}

tasks.withType<ProcessResources> {
    filteringCharset = Charsets.UTF_8.name()
}

tasks.withType<ShadowJar> {
    listOf(
        "com.tchristofferson",
        "dev.dbassett",
        "net.kyori",
        "org.projectjombok"
    ).forEach { relocate(it, "me.astralisvox.astralibs.libs.$it") }

    archiveFileName.set("${project.name}-${project.version}.jar")
}
