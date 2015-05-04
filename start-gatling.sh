[ -e ./gatling ] || {
    echo "Downloading gatling"
    mkdir gatling
    cd gatling
    curl https://repo1.maven.org/maven2/io/gatling/highcharts/gatling-charts-highcharts-bundle/2.1.5/gatling-charts-highcharts-bundle-2.1.5-bundle.zip -o gatling.zip
    unzip gatling.zip
}

cd gatling/gatling-charts-highcharts-bundle-2.1.5 && sh ./bin/gatling.sh