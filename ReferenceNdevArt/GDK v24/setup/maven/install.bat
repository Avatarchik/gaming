call mvn install:install-file ^
-Dfile=dependencies/jars/google-closure-compiler-plugin-1.1.jar ^
-DpomFile=dependencies/jars/google-closure-compiler-plugin.pom.xml

call mvn install:install-file ^
-Dfile=dependencies/jars/gamemanifest-maven-plugin-1.2.jar ^
-DpomFile=dependencies/jars/gamemanifest-maven-plugin.pom.xml

call mvn install:install-file ^
-Dfile=dependencies/jars/rgf-manifest-maven-plugin-1.7.jar ^
-DpomFile=dependencies/jars/rgf-manifest-maven-plugin.pom.xml

call mvn install:install-file ^
-Dfile=dependencies/swcs/chartwell_api-4.1.337.swc ^
-DgroupId=chartwell.clientlibraries.casino4 ^
-DartifactId=chartwell_api ^
-Dversion=4.1.337 ^
-Dpackaging=swc

call mvn install:install-file ^
-Dfile=dependencies/swcs/chartwell_coresdk-4.1.337.swc ^
-DgroupId=chartwell.clientlibraries.casino4 ^
-DartifactId=chartwell_coresdk ^
-Dversion=4.1.337 ^
-Dpackaging=swc

call mvn install:install-file ^
-Dfile=dependencies/swcs/chartwell_debug-1.0.11.swc ^
-DgroupId=chartwell.clientlibraries ^
-DartifactId=chartwell_debug ^
-Dversion=1.0.11 ^
-Dpackaging=swc

call mvn install:install-file ^
-Dfile=dependencies/swcs/chartwell_lang-1.1.90.swc ^
-DgroupId=chartwell.clientlibraries ^
-DartifactId=chartwell_lang ^
-Dversion=1.1.90 ^
-Dpackaging=swc

call mvn install:install-file ^
-Dfile=dependencies/swcs/fl-1.0.swc ^
-DgroupId=flex ^
-DartifactId=fl ^
-Dversion=1.0 ^
-Dpackaging=swc

call mvn install:install-file ^
-Dfile=dependencies/swcs/flashintegration-2.12.swc ^
-DgroupId=amaya.client.library ^
-DartifactId=flashintegration ^
-Dversion=2.12 ^
-Dpackaging=swc


PAUSE