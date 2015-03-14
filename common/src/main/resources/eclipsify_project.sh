# cd ../../../../WebCrawlerServerCommon
# mvn source:jar install
# cd ../WebCrawlerServerMaster
# cd ../../../../common
# mvn source:jar install
cd ../../..
mvn eclipse:eclipse -DdownloadSources=true
