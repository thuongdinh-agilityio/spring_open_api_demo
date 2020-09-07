# Gets the project root.
export DO_PROJ_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# Misc variables
DO_API_DOCKER_IMG="agilityio/open-api-demo"

# =============================================================================
# Java
# =============================================================================

# Go back to home directory
function do-api-build {
  pushd .
  cd "$DO_PROJ_DIR/api" || exit
  gradle bootBuildImage --imageName=$DO_API_DOCKER_IMG
  popd || exit
}

# Run API server
function do-api-run {
  # Do stop before run to avoid port conflict
  do-api-stop
  docker run -d -p 8080:8080 -t $DO_API_DOCKER_IMG
}

# Both build & run the api
function do-api-build-run {
  do-api-stop
  do-api-build
  do-api-run
}

# Stop API server
function do-api-stop {
  docker stop $(docker ps -a -q --filter ancestor=$DO_API_DOCKER_IMG --format="{{.ID}}") > /dev/null 2>&1
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
  cd "$DO_PROJ_DIR/web" || exit
  ng test api
  popd || exit
}

# Bootstraping the local environment for Angular project
function do-web-bootstrap-env() {
  pushd .
  cd "$DO_PROJ_DIR/web" || exit
  npm install -g @angular/cli
  npm install -g @openapitools/openapi-generator-cli
  npm install
  popd || exit
}