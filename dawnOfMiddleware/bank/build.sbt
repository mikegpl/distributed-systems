name := "bank"

version := "0.1"

scalaVersion := "2.12.6"

val grpcVersion = "1.11.0"
val thriftVersion = "0.11.0"

val grpcDeps = Seq(
  "io.grpc" % "grpc-netty" % grpcVersion,
  "io.grpc" % "grpc-protobuf" % grpcVersion,
  "io.grpc" % "grpc-stub" % grpcVersion
)

val thriftDeps = Seq("org.apache.thrift" % "libthrift" % thriftVersion)

val otherDeps = Seq("io.reactivex" %% "rxscala" % "0.26.4")

libraryDependencies ++= grpcDeps
libraryDependencies ++= thriftDeps
libraryDependencies ++= otherDeps
