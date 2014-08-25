--- Setting up the MockCGS Development Environment ---
A basic setup of the MockCGS Development Environment consists of 2 web application servers.
One of the web application servers will be the RGF Game Container, which will host only the RGF Games and RGF Support
packages.
The second, will host the MockCGS web application.

--- Setting up the RGF Game Container Application Server ---
1) Configure the web application server to listen to a non http/https standard port. The RGF Game Container should
not be accessible to the players of the Casino Gaming System (CGS). By listening to a non standard port, this simulates
the expected behaviour.

2) Deploy RGF Support Packages to the webapps directory.

3) Deploy any RGF Game applications to the webapps directory.

4) Start the web application server.


--- Setting up the MockCGS Application Server  ---
1) Deploy the casino webapps to the web application server:
    * casino.war
    * userinfo.war
    * replay.war
    * location.war

2) At the root level of the web application server, generate the following file path (a sample is provided):
    * For Resin, this is the Resin base install directory
    * For Tomcat this is the <tomcat_install>/bin directory 
      (if a startup script starts tomcat at root, then use <tomcat_install> root directory)
        * To correct find identify where the tomcat root directory is, simply start tomcat and the resources will be created.
	
    resources/system/DirectoryService.xml
    resources/system/RemoteGameService.xml

3) DirectoryService.xml will have the following content:

    <directoryService>
        <service key="GAME_STATIC_CONTAINER">http://<webappserver>[<:port>]/casino/</service>
        <service key="GAME_CONTAINER"       >http://<webappserver>[<:port>]/</service>
    </directoryService>

    Note that the GAME_CONTAINER address points to the RGF Game Container application server that was setup in the
    previous section.

4) RemoteGameService.xml will have the following content:

    <remoteGameService>
        <remoteGame name="<gameWebAppName1>">
            <detail key="profileIdStartRange">1000</detail>
        </remoteGame>
        <!--
        <remoteGame name="<gameWebAppName2>">
            <detail key="profileIdStartRange">2000</detail>
        </remoteGame>
        -->
    </remoteGameService>

5) Add aspectjweaver to the webapp server's JVM arguments:
    * For Resin, this is a <jvmArg> element in resin.xml
    * For Tomcat it's easiest just to create a JAVA_OPTS environment variable with this as the value.

    -javaagent:<path-to-aspectJ>/aspectjweaver.jar

6) Start the web application server.

7) Launch Page is located at: http://<webappserver>[<:port>]/casino/launch

8) All data files listed under ./resources/player/* can be deleted at any time.

9) Certain web containers need to have cross context support enabled. Please enable this feature.
    * Tomcat: Update /conf/context.xml to include the attribute crossContext="true"
        - <Context crossContext="true">
    * Resin: Enabled by default