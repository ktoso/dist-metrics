package com.monitor.build

import sbt._
import Keys._

object DefaultBuildSettings {
  import Resolvers._

  val mongoDirectory = SettingKey[File]("mongo-directory")

  val defaultBuildSettings = Defaults.defaultSettings ++ Seq (
      organization  := "pl.project13.distmetrics",
      scalaVersion  := "2.9.1",
      resolvers     := kiwiResolvers,
      scalacOptions := Seq("-unchecked", "-deprecation")
//    ,parallelExecution := false
    )
}
