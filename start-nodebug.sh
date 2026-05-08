#!/bin/bash

java \
    -Xss128M \
    -cp "target/lama-truffle-1.0.0.jar:target/dependency/*" com.lama.truffle.App "$1"