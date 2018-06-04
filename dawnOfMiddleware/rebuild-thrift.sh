#!/bin/bash
rm -rf bank/gen-java
rm -rf client/gen_py

thrift --gen java bank.thrift
thrift --gen py bank.thrift

mv gen-java bank
mv gen-py client/gen_py
