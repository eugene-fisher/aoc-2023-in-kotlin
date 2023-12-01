plugins {
    kotlin("jvm") version "1.9.20"
}
dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
}


sourceSets {
    main {
        kotlin.srcDir("src")
    }
    test {
        kotlin.srcDir("src/test")
    }
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
    test {
        useJUnitPlatform()
    }
}
