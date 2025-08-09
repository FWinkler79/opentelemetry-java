subprojects {
  // Workaround https://github.com/gradle/gradle/issues/847
  group = "io.opentelemetry.api"
  val proj = this
  plugins.withId("java") {
    configure<BasePluginExtension> {
      archivesName.set("opentelemetry-api-${proj.name}")
    }
  }
  tasks.withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
  }
}
