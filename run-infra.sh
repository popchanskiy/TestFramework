#!/bin/sh

set -eu

ENV_FILE="${ENV_FILE:-.env.docker}"
ACTION="${1:-up}"

if [ ! -f "${ENV_FILE}" ]; then
  echo "Env file not found: ${ENV_FILE}" >&2
  exit 1
fi

case "${ACTION}" in
  up)
    docker compose --env-file "${ENV_FILE}" up -d --wait
    ;;
  down)
    docker compose --env-file "${ENV_FILE}" down
    ;;
  restart)
    docker compose --env-file "${ENV_FILE}" down
    docker compose --env-file "${ENV_FILE}" up -d --wait
    ;;
  ps)
    docker compose --env-file "${ENV_FILE}" ps
    ;;
  logs)
    docker compose --env-file "${ENV_FILE}" logs -f
    ;;
  *)
    echo "Unsupported action: ${ACTION}" >&2
    echo "Usage: ./run-infra.sh [up|down|restart|ps|logs]" >&2
    exit 1
    ;;
esac
