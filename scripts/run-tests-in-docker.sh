#!/bin/bash
mkdir coverage
docker run --rm -v `pwd`/coverage:/coverage-out  citest chmod +x scripts/test.sh && scripts/test.sh
