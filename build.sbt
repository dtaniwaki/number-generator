lazy val runGenerator = taskKey[Unit]("Run generator")
lazy val runAggregator = taskKey[Unit]("Run aggregator")

lazy val root = (project in file(".")).
  settings(
    name := "number-generator",
    version := "1.0",
    scalaVersion := "2.11.8",
    mainClass in (Compile, run) := Some("com.github.dtaniwaki.number_generator.Generator"),
    libraryDependencies ++= Seq(
      "com.typesafe.scala-logging"    %%  "scala-logging"     % "3.1.0",
      "org.slf4j"                     %   "slf4j-simple"      % "1.7.21",
      "com.typesafe"                  %   "config"            % "1.3.0",
      "net.ceedubs"                   %%  "ficus"             % "1.1.2",
      "io.spray"                      %%  "spray-json"        % "1.3.2"
    ),
    fullRunTask(runGenerator, Runtime, "com.github.dtaniwaki.number_generator.Generator"),
    fullRunTask(runAggregator, Runtime, "com.github.dtaniwaki.number_generator.Aggregator")
  )
