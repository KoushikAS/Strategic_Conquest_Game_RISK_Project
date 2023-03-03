#!/bin/bash
mkdir coverage
<<<<<<< HEAD
docker run --rm -v `pwd`/coverage:/coverage-out  citest scripts/test.sh
=======
docker run --rm -v `pwd`/coverage:/coverage-out  citest chmod +x scripts/test.sh && scripts/test.sh
>>>>>>> test
