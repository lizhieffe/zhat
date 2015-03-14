cd ../../../../common
mvn source:jar install
cd ../server
mvn eclipse:eclipse -DdownloadSources=true
