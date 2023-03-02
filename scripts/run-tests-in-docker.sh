#!/bin/bash
mkdir coverage
docker images ls
docker run --rm -v `pwd`/coverage:/coverage-out  citest scripts/test.sh
