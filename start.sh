#!/bin/bash

-p="${PORT}":"${PORT}" --name=jeto-api --env-file=.env --memory=384m jeto-img:latest