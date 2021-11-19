#!/bin/bash

function switch_proxy() {

    IDLE_PORT=${1}

    echo "> Target Port: $IDLE_PORT"
    echo "> Port change"
    echo "set \$service_url http://127.0.0.1:$IDLE_PORT;" | sudo tee /etc/nginx/conf.d/service-url.inc

    echo "> nginx Reload"
    sudo service nginx reload
}