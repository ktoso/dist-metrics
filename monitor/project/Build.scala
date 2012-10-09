import sbt._
import Keys._
import ProguardPlugin._
import com.github.siasia.WebPlugin._

import com.monitor.build._

object MonitorBuildSettings {

  // See https://github.com/siasia/xsbt-proguard-plugin
  val proguardSettings = ProguardPlugin.proguardSettings ++ Seq(
    proguardOptions ++= Seq("-include proguard.cfg")
  )

  val buildSettings = DefaultBuildSettings.defaultBuildSettings ++
    proguardSettings
}

object MonitorBuild extends Build {
  import Dependencies._
  import MonitorBuildSettings._
  
  lazy val root: Project = Project(
    "root",
    file("."),
    settings = buildSettings
  ) aggregate(monitor, common)

  lazy val common: Project = Project(
    "common",
    file("common"),
    settings = buildSettings ++
      Seq(
        libraryDependencies ++= Seq(scalaz, guava, protoBuf) ++ testing ++ logging
      )
  )

  lazy val monitor: Project = Project(
    "monitor",
    file("monitor"),
    settings = buildSettings ++
      Seq(
        libraryDependencies ++= akkaFull ++ testing ++ Seq(sprayServer, sprayCan)
      ) ++
      Seq(
        mainClass in (Compile, packageBin) := Some("pl.project13.distmetrics.monitor.runner.Main")
      )
  ) dependsOn (common)

}
