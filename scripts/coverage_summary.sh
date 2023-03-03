#!/bin/bash


emacs --batch -u `whoami` --script scripts/docov.el

echo "TOTAL COVERAGE: ${cv}%"
