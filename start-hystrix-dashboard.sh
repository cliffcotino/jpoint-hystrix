[ -e ./hystrix ] || {
    echo "Checking out hystrix"
    git clone git@github.com:Netflix/Hystrix.git hystrix
}

cd hystrix/hystrix-dashboard
../gradlew jettyRun