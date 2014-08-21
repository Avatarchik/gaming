The Casino 5 Client Development Harness

I. Description

The purpose of the Casino 5 Client Development Harness is to allow client developers to perform high-frequency
testing cycles of the client during development. This is accomplished by building the harness in one of two ways
(depending on your needs).

  - Standalone: This mode compiles the game and runs it in the browser. All server responses are read from local xml
                files. This allows the client to run purely on the local machine.

                Standalone mode is frequently used during the early stages of development when the server portion of the
                game has not yet been developed to a point that allows it to service requests. As long as the XML format
                of the messages has been determined, a client developer can mock the responses in XML files and change
                the values as needed. This allows the client to begin handling real game flow early while rapidly
                testing changes.

  - Live: This mode compiles the game and runs it in the browser. All server communication is sent to a specified server
          that is running the game. This allows the client to run using local client files (as opposed to those on the
          server) while still making use of a real server's messaging.

          Live mode is typically used when the server portion of the game is completed to a point that allows it to
          service the necessary requests. The client developer can run his local changes against the live server without
          needing to upload the changes. This allows rapid testing of changes and allows the server to run a stable
          version of the client for others to use (QA, Server developers etc).


II. Requirements

o General

Both Standalone and Live modes require that client Casino 5 Framework files be downloaded from a running instance of the
Amaya Gaming System (AGS). This only needs to be done once before running in either mode. This step may need to
be repeated if a significant client framework upgrade is deployed on the AGS.


o Standalone Mode

Standalone mode requires mock files to be created as well as a special file named "map.xml". These files must be
placed in the root of the harness/webapp/casino5/standalone directory. At a minimum, the following standalone XML files are
required to load the game.

    - map.xml
    - checklogin.xml
    - login.xml
    - profile.xml
    - startgame.xml
    - confirmhandend.xml

Additional files are required to perform resume in standalone mode.

    - preresume.xml
    - resume.xml

The files above are pre-installed in the webapp/casino4/standalone directory. They will require some minor editing to work
with your specific game. See the "Creating Standalone Response Files" section.

Each game request will require at least one of its own standalone response files as well. Each game will require at
least one request and may require several. This depends on the design of the game. It is recommended that client and
developers work out the messaging for the game before coding begins.


o Live Mode

Live mode requires that the server portion of your game is running on a server that you can connect to. All client
files are run locally from the developers machine while any requests are proxied to the configured AGS environment. To
properly connect in live mode, the game will need the following information from the AGS environment.

    - A valid playerhandle
    - The gameId of the game
    - The account to use (USD, EUR, GBP)
    - The domain of the server (www.agsserver.com)
    - The protocol to use when connecting (http/https)

Live mode also checks for resumable (unfinished) games each time the harness is run. If a resume state is returned when
the harness is run, that unfinished state will be resumed. This check can be disabled by adding "resumedGame=0" to the
query string which will force the harness to start a new game.



III. Installation

The harness can be run from any location provided it is properly configured. However, since the harness is configured
with game-specific settings and files, it is recommended that the harness remain in the pre-installed location: the trunk/client/casino5
directory of the Remote Game Framework (RGF) project. For example, given an RGF project called "MySlotGame", the harness would be
installed as follows:

    - MySlotGame
        - client
            - casino5
                - harness
                    - harness files/directories
                - src
        - server



IV. The Harness Directory Structure

The harness contains a number of pre-defined directories and files as well as locations for your own game-specific
files to be stored. The following is a list of the files and directories under "harness".

    - resources - Contains templates used during the build and the cache of files downloaded during preparation.
    - webapp/casino5/standalone - Stores the standalone response XML files used by the standalone harness.
    - build.xml - The Ant build file used to build the harness.
    - connection.properties - Properties file used by the build (See "Configuration").
    - standalone.properties - Properties file used by the build (See "Configuration").
    - live.properties - Properties file used by the build (See "Configuration").
    - webserver.xml - The Ant build file used to start and stop the Tomcat webserver.
    - webserver.properties - Properties file used by the webserver.xml build.
    - README.txt - This file.



V. Configuration

The Casino 5 Client Development Harness uses four configuration files to store game and environment-specific settings. All
files are stored in the root of the harness directory.

o connection.properties

  This file stores the information used during the "prepare" step of the setup.

o standalone.properties

  This file stores the values that are passed to the game client at startup when running the Standalone harness. Each
  time the harness is built, these values are written into the page that runs the game. This file contains the following
  properties (which are documented within as well).

    - displayName - The displayable name of the game. Appears on the loading screen.
    - gameName - The start game name of the game as defined in the DB.
    - lang - The 2-character language code to use when playing the game.
    - account - The 3-character account code to use. In Standalone mode, the value is only used for currency formatting.
    - resumedGame - Indicates if the game should resume an unfinished game ("1") or start a new one ("0").
    - mode - Mode should remain set at "casino5".

o live.properties

  This file stores the values that are passed to the game client at startup when running the Live harness. Each time
  the harness is built, these values are written into the page that runs the game.

    - playerHandle - A valid playerhandle to use when launching a game.
    - displayName - The displayable name of the game. Appears on the loading screen.
    - gameName - The start game name of the game as defined in the DB.
    - gameId - The game id of the game as defined in the DB.
    - domain - The domain of the server running a AGS instance that will handle messaging.
    - protocol - The protocol to use when connecting to the server (http or https).
    - lang - The 2-character language code to use when playing the game.
    - account - The 3-character account code to use.
    - mode - Mode should remain set at "casino5".

o webserver.properties

  This file stores the values that define the properties used by the Tomcat webserver instance. Be sure to use forward slashes in all paths.

    - tomcat.home - Path to the Tomcat directory. This is configured by default and does not need to be changed.
    - tomcat.start.port - Port to use when running Tomcat. Default is 9999. This value does not need to be changed but can be modified if this port
                         conflicts with another process running on the machine.
    - tomcat.stop.port - Port to use when stopping Tomcat. Default is 9998. This value does not need to be changed but can be modified if this port
                        conflicts with another process running on the machine.

VI. Building

Building the development harness is a two-step process. The first step, preparation, is typically executed
once and then does not need to be repeated until a new build of the CGS environment is deployed. The second step,
building either the standalone or live harness, is repeated frequently, each time the client code or graphics are
changed and need to be tested.

Both steps are executed by running the applicable target of the Ant build.xml file located in the root of the harness
directory. This can be done from the command line or from the IDE.

o Step 1: Preparation

  The preparation step connects to the configured AGS environment and downloads a number of files to a cache directory.
  Once completed the next step of the build process will utilize these files when packing the harness. To execute the
  preparation step, run the "build.prepare" target of the build.xml file.

  * NOTE: The Game Development Platform package pre-runs this step when it is built. This only needs to be run if
    you require an update to the framework files from the AGS.

o Step 2: Building the Harness

  The harness build step involves running either the "build.standalone" or "build.live" targets of the build.xml file,
  depending on how you want server communication to be handled at runtime. In either case, the artifacts of the build
  (the game files, framework files and test page) are packaged in the resources/tools/tomcat/webapps/standalone or 
  resources/tools/tomcat/webapps/live directories. The basic process that occurs in either case is as follows.

    - The build ensures that the preparation step has been completed.
    - The output directory of the harness is destroyed. (webapps/standalone or webapps/live)
    - The framework and harness page files are copied to the output directory.
    - The properties from either standalone.properties or live.properties are substituted into the harness page.
    - The game client is built to a temporary location.
    - The game client webapp is assembled in the tomcat/webapps directory.
    - The game client temporary output is destroyed.

  If the build completes successfully, the harness.html file located in the appropriate output directory can be
  launched.



VII. Running the Harness

To run the harness, you must first start the built-in Tomcat instance. Start Tomcat by running the "start" target in the
webserver.xml Ant build. Once running, either the standalone or live harness can be launched (provided the appropriate build
has already been executed).

    - Standalone: http://localhost:9999/standalone/harness.html
    - Live: http://localhost:9999/live/harness.html

For convenience, the Ant build provides two targets for launching the harness; "launch.standalone" and "launch.live".
These targets simply launch the URL's above in the default browser.

To stop the Tomcat instance, either close the command window or run the "stop" target of the webserver.xml Ant build 


o Start Up Properties

The properties required to start the game are embedded into the page so you do not have to add any query string
parameters to the url to start the game (The embedded values are the ones set in standalone.properties and
live.properties).


To obtain a valid playerHandle for live mode there are two options, both of which utilize the server's SDK API. 

Option 1: Use the following url in a browser

<protocol>://<server>/sdk/servlet/com.chartwelltechnology.icd.ClientLoginSupportServlet?method=login&uid=<player name>&account=<account>&country=US&lsdId=zero&xmlresponse=true&bal=<balance>

Substitute the tokens in the url with valid values:

protocol: http or https
server: The server's domain such as opal.chartwelltech.com
player name: Any alphanumeric string. If the player specified does not exist it will be created.
account: 3-character account value such as USD, GBP, CAD etc
balance: The amount of money to add to the player's account. This value is optional.


Option 2: Use the playerhandle.air application

A GUI has been developed that can be installed and run to provide a form-based interface for creating playerHandles. 
The application is part of the Game Development Platform package provided by Amaya Gaming.


o Advanced

If required, embedded parameters can be overriden by specifying them on the url's query string using the following keys:

    - playerHandle
    - account
    - gameName
    - gameId
    - displayName
    - domain
    - protocol
    - lang
    - resumedGame


VIII. Creating Standalone Response Files

Standalone response files are XML files that define the data to be returned when a corresponding request is made from
the game. These files are loaded from the web/standalone directory.

o System Responses

Several of the responses used by the game are "system" responses. That is, their structure is strictly defined.

    - profile.xml
    - checklogin.xml
    - login.xml
    - startgame.xml
    - preresume.xml
    - resume.xml
    - confirmhandend.xml

For these responses, a response XML file has been pre-created in the web/standalone directory. The profile.xml file
will requires one minor edit before the game will load in standalone mode. open the the profile.xml and set the value
of the "profile" node's "gameName" attribute to the start game name of the game. Be sure this value matches the gameName
value defined in the standalone.properties file.


o Map.xml

Matching of requests to response XML files is done using a special file called "map.xml". The map defines
at least one response for each request and, if required, can define multiple responses. A default map has been
pre-created in the web/standalone directory that defines the system responses. The custom game responses written
during development will be added to the map.

The map consists of N number of "responses" node. Each node defines the request name as well as one or more "response"
nodes that define the file to load.

    <responses request="RequestName">
        <response id="RequestName" file="ResponseFile.xml"/>
    </responses>

If the "responses" node contains multiple "response" nodes, the responses will be cycled through sequentially. When
all responses have been used, the next request will start over at the first response. This allows the developer to
script sequences such as multiple slot machine spins. Each response is loaded from the file specified by the value of
the "file" attribute.

Example:

    <responses request="RequestName">
        <response id="RequestName" file="ResponseFile1.xml"/>
        <response id="RequestName" file="ResponseFile2.xml"/>
        <response id="RequestName" file="ResponseFile3.xml"/>
        <response id="RequestName" file="ResponseFile4.xml"/>
    </responses>


o Adding Custom Responses

Adding new responses for a game's specific requests (such as the "SpinReq" of a slot machine) is as simple as adding a
new "responses" node to the map and creating the response files. The map can contain as many "responses" nodes as is
required to service all requests the game might make.

Example:

    <responses request="SpinReq">
        <response id="SpinReq" file="SpinResponse.xml"/>
    </responses>



o Response Headers

Each response served by the AGS contains two distinct parts; the message header and the message body. The message header
is never seen by the game but is used by the framework to validate the response. Typically, you should not need to edit
the header but the header does need to exist in the response.


o Limitations

Each response carries a balance with it that is handled by the framework and passed to the game frequently. Due to the
fact that standalone mode does not communicate with the server, the balance will always reflect the value of the
<balance> node in the header of each response. It is possible to script basic balance changes this way but it is best
to use live mode to test scenarios that are dependent on balance accuracy.



IX. Troubleshooting

- The harness seems to freeze at startup

    Check the JavaScript console in your browser for a JavaScript exception.

- The game begins to load but the in-game error screen appears.

    Note the information listed under Debug. There are numerous errors that
    can occur but this line should help narrow down what the problem is. You may also want to note the 3-digit "Error
    Code" located to the right of the "Contact Support" button and refer to the error code documentation.

- The game displays Error Code 014 in Standalone mode

    The value of gameName in standalone.properties does not match the value of the gameName attribute in the profile
    node of profile.xml.

- A "Live" mode harness does not resume an unfinished game (i.e. the window was closed mid-game)

    First, games take 60 seconds to become resumable. Be sure you have waited 60 seconds or more after closing the window
    before expecting the game to resume. If the game still does not resume after 60 seconds, be sure that you do not have
    any other games in a resumable state. When running in live mode, the harness will check the server for resumable
    games but the server will only return one game (not all resumable games). Since the harness can only play the game
    in development, it will ignore resumeable states from other games.