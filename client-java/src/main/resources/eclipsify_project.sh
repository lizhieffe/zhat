cd ../../../../common
mvn source:jar install
cd ../client-java
mvn eclipse:eclipse -DdownloadSources=true
