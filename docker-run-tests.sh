#!/bin/sh

set -eu

if [ "$#" -gt 0 ]; then
  exec "$@"
fi

GROUPS_ARG=""
if [ -n "${TEST_GROUPS:-}" ]; then
  GROUPS_ARG="-Dgroups=${TEST_GROUPS}"
fi

EXCLUDED_GROUPS_ARG=""
if [ -n "${EXCLUDED_GROUPS:-}" ]; then
  EXCLUDED_GROUPS_ARG="-DexcludedGroups=${EXCLUDED_GROUPS}"
fi

MAVEN_ARGS="${MAVEN_ARGS:-}"

set +e
mvn -B test ${GROUPS_ARG} ${EXCLUDED_GROUPS_ARG} ${MAVEN_ARGS}
TEST_EXIT_CODE=$?
set -e

if [ -d "target/surefire-reports" ]; then
  mvn -B surefire-report:report-only || true
fi

exit "${TEST_EXIT_CODE}"
