echo 'Building & Publishing The Bookshelf...'
echo ''

DOCKER_HUB_USER=urjcmpuchades

# bookshelf monolith
echo "Build & publish bookshelf monolith..."
BOOKSHELF_MONOLITH_IMAGE_NAME="${DOCKER_HUB_USER}/bookshelf-monolith:v1.0"

mvn -f bookshelf-monolith/ compile jib:build -Dimage=${BOOKSHELF_MONOLITH_IMAGE_NAME}

# user-service monolith
echo "Build & publish user-service monolith..."
USER_SERVICE_IMAGE_NAME="${DOCKER_HUB_USER}/user-service:v1.0"

mvn -f user-service-monolith/ compile jib:build -Dimage=${USER_SERVICE_IMAGE_NAME}

exit 0