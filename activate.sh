# Gets the project root.
export DO_PROJ_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )";

# Misc variables
DO_API_DOCKER_IMG="agilityio/open-api-demo"

# =============================================================================
# Java
# =============================================================================

# Go back to home directory
function do-api-build {
  pushd .
  cd $DO_PROJ_DIR/api
  gradle bootBuildImage --imageName=$DO_API_DOCKER_IMG
  popd
}

# Run API server
function do-api-run {
  # Do stop before run to avoid port conflict
  do-stop-api
  docker run -d -p 8080:8080 -t $DO_API_DOCKER_IMG
}

# Both build & run the api
function do-api-build-run {
  do-stop-api
  do-build-api
  do-run-api
}

# Stop API server
function do-api-stop {
  docker stop $(docker ps -a -q --filter ancestor=$DO_API_DOCKER_IMG --format="{{.ID}}")
}

# =============================================================================
# API Generator
# =============================================================================

# Gen API with latest API definition
function do-api-gen {
  openapi-generator generate \
    -g typescript-angular \
    -i http://localhost:8080/api-docs \
    -o web/libs/api/src/lib \
    --additional-properties ngVersion=10.0.8
}

# =============================================================================
# Angular
# =============================================================================

# Run test for API lib
function do-web-run-api-test() {
  pushd .
  cd $DO_PROJ_DIR/web || exit
  ng test api
  popd || exit
}