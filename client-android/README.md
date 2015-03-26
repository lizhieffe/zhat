1. When using mvn to compile or package, one might see error:

java.lang.NoClassDefFoundError: org/eclipse/aether/spi/connector/Transfer$State

In this case, install an older version of Maven:

brew install homebrew/versions/maven31
sudo ln -s /usr/local/Cellar/maven31/3.1.1 /usr/local/Cellar/maven/
brew switch maven 3.1.1
