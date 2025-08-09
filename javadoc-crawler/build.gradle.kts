plugins {
  id("otel.java-conventions")
}

dependencies {
  implementation("com.fasterxml.jackson.core:jackson-databind")
  testImplementation("org.assertj:assertj-core:3.27.4")
}

description = "OpenTelemetry Javadoc Crawler"
otelJava.moduleName.set("io.opentelemetry.javadocs")

tasks {
  withType<JavaCompile>().configureEach {
    sourceCompatibility = "21"
    targetCompatibility = "21"
    options.release.set(21)
  }

  // only test on java 21+
  val testJavaVersion: String? by project
  if (testJavaVersion != null && Integer.valueOf(testJavaVersion) < 21) {
    test {
      enabled = false
    }
  }

  val crawl by registering(JavaExec::class) {
    dependsOn(classes)

    mainClass.set("io.opentelemetry.javadocs.JavaDocsCrawler")
    classpath(sourceSets["main"].runtimeClasspath)
  }
}
