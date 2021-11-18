#!/usr/bin/env bash

# 'real' is used = 'real1' is available
# 'real1' is used = 'real' is available
function find_idle_profile()
{
    RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:880/profile)

    if [ ${RESPONSE_CODE} -ge 400 ] # 400 보다 크면 (즉, 40x/50x 에러 모두 포함)
    then
        CURRENT_PROFILE=real1
    else
        CURRENT_PROFILE=$(curl -s http://localhost:880/profile)
    fi

    if [ ${CURRENT_PROFILE} == real ]
    then
      IDLE_PROFILE=real1
    else
      IDLE_PROFILE=real
    fi

    echo "${IDLE_PROFILE}"
}

# find available port
function find_idle_port()
{
    IDLE_PROFILE=$(find_idle_profile)

    if [ ${IDLE_PROFILE} == real ]
    then
      # real1 profile's port is available
      echo "8081"
    else
      # real profile's port is available
      echo "8080"
    fi
}