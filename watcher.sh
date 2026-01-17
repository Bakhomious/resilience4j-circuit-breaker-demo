#!/usr/bin/env bash

watch -n 1 "curl -s http://localhost:8080/actuator/circuitbreakers | jq"
