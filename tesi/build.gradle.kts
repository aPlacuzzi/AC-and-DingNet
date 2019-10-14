plugins {
    java
    kotlin("jvm") version "1.3.41"
}

repositories { mavenCentral() }

dependencies {
    implementation("org.protelis:protelis:${extra["protelisVersion"].toString()}")
    implementation("com.javadocmd:simplelatlng:1.3.1")
    implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:${extra["paho_version"].toString()}")
    implementation("com.google.code.gson:gson:${extra["gsonVersion"].toString()}")
    implementation("com.typesafe.akka:akka-actor_2.12:${extra["akkaVersion"].toString()}")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:${extra["kotlinTestVersion"].toString()}")
    testImplementation("io.mockk:mockk:1.9.1")
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
}