#!/bin/sh

set -eu

SCRIPT_DIR=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
ENV_FILE="${ENV_FILE:-.env.docker}"
IMAGE_NAME_OVERRIDE="${IMAGE_NAME-__UNSET__}"
NETWORK_NAME_OVERRIDE="${NETWORK_NAME-__UNSET__}"
DB_URL_OVERRIDE="${DB_URL-__UNSET__}"
TEST_GROUPS_OVERRIDE="${TEST_GROUPS-__UNSET__}"
EXCLUDED_GROUPS_OVERRIDE="${EXCLUDED_GROUPS-__UNSET__}"
MAVEN_ARGS_OVERRIDE="${MAVEN_ARGS-__UNSET__}"

case "${ENV_FILE}" in
  /*)
    ;;
  *)
    if [ -f "${ENV_FILE}" ]; then
      ENV_FILE="$(pwd)/${ENV_FILE}"
    elif [ -f "${SCRIPT_DIR}/${ENV_FILE}" ]; then
      ENV_FILE="${SCRIPT_DIR}/${ENV_FILE}"
    fi
    ;;
esac

if [ ! -f "${ENV_FILE}" ]; then
  echo "Env file not found: ${ENV_FILE}" >&2
  exit 1
fi

set -a
. "${ENV_FILE}"
set +a

if [ "${IMAGE_NAME_OVERRIDE}" != "__UNSET__" ]; then
  IMAGE_NAME="${IMAGE_NAME_OVERRIDE}"
fi

if [ "${NETWORK_NAME_OVERRIDE}" != "__UNSET__" ]; then
  NETWORK_NAME="${NETWORK_NAME_OVERRIDE}"
fi

if [ "${DB_URL_OVERRIDE}" != "__UNSET__" ]; then
  DB_URL="${DB_URL_OVERRIDE}"
fi

if [ "${TEST_GROUPS_OVERRIDE}" != "__UNSET__" ]; then
  TEST_GROUPS="${TEST_GROUPS_OVERRIDE}"
fi

if [ "${EXCLUDED_GROUPS_OVERRIDE}" != "__UNSET__" ]; then
  EXCLUDED_GROUPS="${EXCLUDED_GROUPS_OVERRIDE}"
fi

if [ "${MAVEN_ARGS_OVERRIDE}" != "__UNSET__" ]; then
  MAVEN_ARGS="${MAVEN_ARGS_OVERRIDE}"
fi

IMAGE_NAME="${IMAGE_NAME:-iapopov/testframework-runner:1.0}"
NETWORK_NAME="${NETWORK_NAME:-nbank-network}"
DB_URL="${DB_URL:-jdbc:postgresql://postgres:5432/${DB_NAME:-nbank}}"

mkdir -p target
docker run --rm \
  -v "$(pwd)/target:/target" \
  --entrypoint sh \
  "${IMAGE_NAME}" \
  -c 'find /target -mindepth 1 -maxdepth 1 -exec rm -rf {} +'

set -- \
  --rm \
  --network "${NETWORK_NAME}" \
  -v "$(pwd)/target:/app/target" \
  -e "HOST=${HOST:-http://backend}" \
  -e "PORT=${PORT:-4111}" \
  -e "API_VERSION=${API_VERSION:-1}" \
  -e "ADMIN_USERNAME=${ADMIN_USERNAME:-admin}" \
  -e "ADMIN_PASSWORD=${ADMIN_PASSWORD:-admin}" \
  -e "DB_URL=${DB_URL}" \
  -e "DB_USERNAME=${DB_USERNAME:-postgres}" \
  -e "DB_PASSWORD=${DB_PASSWORD:-postgres}" \
  -e "MOCK_REMOTE=${MOCK_REMOTE:-http://wiremock:8080}" \
  -e "REMOTE=${REMOTE:-http://selenium-hub:4444/wd/hub}" \
  -e "BASE_URL=${BASE_URL:-http://nginx}" \
  -e "BROWSER=${BROWSER:-chrome}" \
  -e "BROWSER_SIZE=${BROWSER_SIZE:-1920x1080}"

if [ -n "${TEST_GROUPS:-}" ]; then
  set -- "$@" -e "TEST_GROUPS=${TEST_GROUPS}"
fi

if [ -n "${EXCLUDED_GROUPS:-}" ]; then
  set -- "$@" -e "EXCLUDED_GROUPS=${EXCLUDED_GROUPS}"
fi

if [ -n "${MAVEN_ARGS:-}" ]; then
  set -- "$@" -e "MAVEN_ARGS=${MAVEN_ARGS}"
fi

docker run "$@" "${IMAGE_NAME}"
