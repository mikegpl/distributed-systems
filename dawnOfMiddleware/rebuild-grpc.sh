#!/bin/bash

rm -rf bank/grpc-gen
mkdir -p bank/grpc-gen
protoc currency.proto --java_out=./bank/grpc-gen