echo 'Building & Publishing The Bookshelf...'
echo ''

DOCKER_HUB_USER=urjcmpuchades

# bookshelf monolith
echo "Build & publish bookshelf monolith..."
BOOKSHELF_MONOLITH_IMAGE_NAME="${DOCKER_HUB_USER}/mca-bookshelf-monolith:v1.0"

cd bookshelf-monolith
docker build -t $BOOKSHELF_MONOLITH_IMAGE_NAME .
docker push $BOOKSHELF_MONOLITH_IMAGE_NAME
cd ..

# user-service monolith
echo "Build & publish user-service monolith..."
USER_SERVICE_IMAGE_NAME="${DOCKER_HUB_USER}/mca-bookshelf-user-service:v1.0"

cd user-service
docker build -t $USER_SERVICE_IMAGE_NAME .
docker push $USER_SERVICE_IMAGE_NAME
cd ..

exit 0