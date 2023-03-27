plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.7.20"
    id("org.jetbrains.intellij") version "1.13.2"

    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.codease"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

configurations {
    create("pluginImplementation") {
        extendsFrom(implementation.get())
        isCanBeResolved = true
    }
}


tasks.shadowJar {
    archiveBaseName.set("com.codease.Codease")
    archiveClassifier.set("")
    archiveVersion.set("1.0.0")
    manifest {
        attributes["Plugin-Version"] = project.version.toString()
    }


    configurations = listOf(project.configurations.getByName("pluginImplementation"))
    mergeServiceFiles()
}


// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2022.1.4")
    type.set("IC") // Target IDE Platform
    pluginName.set("Codease")
//    updateSinceUntilBuild(false)
    plugins.set(listOf(/* Plugin Dependencies */))

//    alternativeIdePath.set("C:/Program Files/Android/Android Studio/bin/studio64.exe")


}

//intellij {
//    setInstrumentCode(false)
//    publish {
//        pluginJar.set(tasks.shadowJar.get().archiveFile.get().asFile)
//    }
//}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    patchPluginXml {
        sinceBuild.set("221")
        untilBuild.set("231.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
//    implementation("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation("io.reactivex.rxjava3:rxjava:3.0.13")
//    // https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp
//    implementation("com.squareup.okhttp3:okhttp:4.9.3")
////    // https://mvnrepository.com/artifact/com.google.code.gson/gson
////    implementation("com.google.code.gson:gson:2.10.1")
//    // https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-gson
//    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
//    // https://mvnrepository.com/artifact/com.squareup.retrofit2/adapter-rxjava3
//    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
//    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    



}

