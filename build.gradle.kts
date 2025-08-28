plugins {
    id("java")
    id("application")
}

group = "org.stratagem"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("org.stratagem.Main")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}