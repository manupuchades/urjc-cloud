#!/bin/bash

echo 'Building & Publishing EoloPlanner...'
echo ''

DOCKER_HUB_USER=urjcmpuchades

# server
echo "Build server..."
SERVER_IMAGE_NAME="${DOCKER_HUB_USER}/eoloplanner-server:v1.0"
docker build -t ${SERVER_IMAGE_NAME} ./server

echo "Publish server..."
docker push ${SERVER_IMAGE_NAME}

# planner
echo "Build planner..."
PLANNER_IMAGE_NAME="${DOCKER_HUB_USER}/eoloplanner-planner:v1.0"
docker build -t ${PLANNER_IMAGE_NAME} ./planner

echo "Publish planner..."
docker push ${PLANNER_IMAGE_NAME}

# toposervice
echo "Build & publish toposervice..."
TOPOSERVICE_IMAGE_NAME="${DOCKER_HUB_USER}/eoloplanner-toposervice:v1.0"

mvn -f toposervice/ compile jib:build -Dimage=${TOPOSERVICE_IMAGE_NAME}


# weatherservice
echo "Build weatherservice..."
WEATHERSERVICE_IMAGE_NAME="${DOCKER_HUB_USER}/eoloplanner-weatherservice:v1.0"
pack build ${WEATHERSERVICE_IMAGE_NAME} --path ./weatherservice --builder gcr.io/buildpacks/builder:v1

echo "Publish weatherservice..."
docker push ${WEATHERSERVICE_IMAGE_NAME}

exit 0