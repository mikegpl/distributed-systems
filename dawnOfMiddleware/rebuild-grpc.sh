#!/bin/bash

rm -rf bank/grpc-gen
mkdir -p bank/grpc-gen
protoc currency.proto --plugin=./protoc-gen-grpc-java --grpc-java_out=./bank/grpc-gen --java_out=./bank/grpc-gen


