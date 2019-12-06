To run the program:

Use intellij to import the project and run com.webcrawler.Main class with root-url as argument

To run from command line use below command:

${JAVA_HOME}/bin/java -cp "target/classes:target/lib/*" com.webcrawler.Main "https://www.rescale.com/"

To change the configuration:

- update src/main/resources/config.yaml
- recompile using mvn clean compiile
- re-run using above command


Current configuration:

numberOfThreads: 4
maxUrls: 2500
batchSize: 5
timeoutMinutes: 2
queueSize: 10000
urlPrefixs:
  - "a href"
  - "http"
urlExcludePatterns:
  - "www.youtube.com"
  - ".css"
  - ".png"
  - ".jpg"

To enable logging update src/main/resources/logging.properties, set the level from INFO to FINE
