import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.3.50"
}

group = "vitaly.com"
version = "1.0-SNAPSHOT"

val ktor_version = "1.2.4"
val tcnative_version = "2.0.25.Final"
val tcnative_classifier = "windows-x86_64" // linux-x86_64, osx-x86_64, windows-x86_64

repositories {
    mavenCentral()
    jcenter()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClassName = "foobar.MainKt"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compile("io.ktor:ktor-server-netty:$ktor_version")
    compile("ch.qos.logback:logback-classic:1.2.3")
//    compile("com.h2database:h2:1.4.199")
    implementation("io.ktor:ktor-network-tls:$ktor_version")
    implementation("io.ktor:ktor-html-builder:$ktor_version")
    implementation("io.netty:netty-tcnative-boringssl-static:$tcnative_version")
    implementation("io.netty:netty-tcnative:$tcnative_version:$tcnative_classifier")
    testCompile(group = "junit", name = "junit", version = "4.12")
}