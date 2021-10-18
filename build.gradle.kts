plugins {
    java
}

group = "org.mail"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.inject:guice:5.0.1")
    implementation("org.jetbrains:annotations:20.1.0")
    implementation("org.projectlombok:lombok:1.18.20")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}