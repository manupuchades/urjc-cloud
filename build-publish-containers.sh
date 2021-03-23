#!/bin/bash

echo 'Building & Publishing EoloPlanner...'
echo ''

DOCKER_HUB_USER=urjcmpuchades

# server
echo "Build server..."
cd server
docker build . -t $DOCKER_HUB_USER/server

echo "Publish server..."
docker push $DOCKER_HUB_USER/server

# planner
echo "Build planner..."
cd ../planner
docker build . -t $DOCKER_HUB_USER/planner
echo "Publish planner..."
docker push $DOCKER_HUB_USER/planner

# toposervice
echo "Build toposervice..."
cd ../toposervice
mvn compile jib:build -Dimage=$DOCKER_HUB_USER/toposervice
echo "Publish toposervice..."
docker push $DOCKER_HUB_USER/toposervice

# weatherservice
echo "Build weatherservice..."
cd ../weatherservice
pack build $DOCKER_HUB_USER/weatherservice --path . --builder gcr.io/buildpacks/builder:v1
echo "Publish weatherservice..."
docker push $DOCKER_HUB_USER/weatherservice