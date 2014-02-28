#!/bin/bash

highlight --no-doc --tab=2 --out-format=latex -l --wrap-simple --zeroes --line-number-length=3 --line-length=62 -B '*.*' --skip='tex;sh;DS_Store'
