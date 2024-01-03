plugins {
    java
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    id("io.freefair.lombok") version "8.4"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    val jetbrainsAnnotationsVersion = "24.0.0"
    implementation("org.jetbrains:annotations:${jetbrainsAnnotationsVersion}")

    // https://mvnrepository.com/artifact/org.codehaus.mojo/jaxb2-maven-plugin
    val jaxb2MavenPluginVersion = "3.1.0"
    implementation("org.codehaus.mojo:jaxb2-maven-plugin:${jaxb2MavenPluginVersion}")

    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
