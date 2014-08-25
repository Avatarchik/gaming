The Flash Client Development Harness

I. Description

The purpose of the Flash Client Development Harness is to allow Flash client developers to perform high-frequency
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

The harness builds in a self-contained manner and can therefore be moved around freely.


II. Requirements

o General

Both Standalone and Live modes require that client "framework" files be downloaded from a running instance of the
Chartwell Gaming System (CGS). This only needs to be done once before running in either mode. This step may need to
be repeated if a significant client framework upgrade is deployed on the CGS.


o Standalone Mode

Standalone mode requires mock files to be created as well as a special file named "map.xml". These files must be
placed in the root of the harness/web/standalone directory. At a minimum, the following standalone XML files are
required to load the game.

    - map.xml
    - login.xml
    - profile.xml
    - startgame.xml
    - confirmhandend.xml

Additional files are required to perform resume in standalone mode.

    - preresume.xml
    - resume.xml

The files above are pre-installed in the web/standalone directory. They will require some minor editing to work
with your specific game. See the "Creating Standalone Response Files" section.

Each game request will require at least one of its own standalone response files as well. Each game will require at
least one request and may require several. This depends on the design of the game. It is recommended that client and
developers work out the messaging for the game before coding begins.


o Live Mode

Live mode requires that the server portion of your game is running on a server that you can connect to. All client
files are run locally from the developers machine while any requests are sent to the configured CGS environment. To
properly connect in live mode, the game will need the following information from the CGS environment.

    - A valid playerhandle
    - The gameId of the game
    - The account to use (USD, EUR, GBP)
    - The domain of the server (www.cgsserver.com)
    - The protocol to use when connecting (http/https)

Live mode also checks for resumable (unfinished) games each time the harness is run. If a resume state is returned when
the harness is run, that unfinished state will be resumed. This check can be disabled by adding "resumedGame=0" to the
query string which will force the harness to start a new game.


III. Installation

The harness can be run from any location provided it is properly configured. However, since the harness is configured
with game-specific settings and files, it is recommended that the harness be copied to client directory of the
Remote Game Framework (RGF) project. For example, given an RGF project called "EGIAdaptor", the harness would be
installed as follows:

    - EGIAdaptor
        - client
            - harness
                - harness files/directories
            - src
        - server



IV. The Harness Directory Structure

The harness contains a number of pre-defined directories and files as well as locations for your own game-specific
files to be stored. The following is a list of the files and directories under "harness".

    - build - Only appears after a build is created and can be destroyed at any time.
    - resources - Contains templates used during the build and the cache of files downloaded during preparation.
    - web/standalone - Initially empty. Stores the standalone response XML files used by the standalone harness.
    - build.xml - The Ant build file used to build the harness.
    - browser.properties - Properties file defining the location of the browsers on the development machine.
    - connection.properties - Properties file used by the build (See "Configuration").
    - standalone.properties - Properties file used by the build (See "Configuration").
    - live.properties - Properties file used by the build (See "Configuration").
    - README.txt - This file.



V. Configuration

The Flash Client Development Harness use 4 configuration files to store game and environment-specific settings. All
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
    - miniGame - Specifies whether this game is a full-size (false) or mini (true) game client.

o live.properties

  Thie file stores the values that are passed to the game client at startup when running the Live harness. Each time
  the harness is built, these values are written into the page that runs the game.

    - playerHandle - A valid playerhandle to use when launching a game.
    - displayName - The displayable name of the game. Appears on the loading screen.
    - gameName - The start game name of the game as defined in the DB.
    - gameId - The game id of the game as defined in the DB.
    - domain - The domain of the server running a CGS instance that will handle messaging.
    - protocol - The protocol to use when connecting to the server (http or https).
    - lang - The 2-character language code to use when playing the game.
    - account - The 3-character account code to use.
    - miniGame - Specifies whether this game is a full-size (false) or mini (true) game client.

o browser.properties

  This file stores the values that define the paths to the browsers on the development machine. This file is only
  used if you choose to use the "launch" targets in the Ant build file. Be sure to use forward slashes in all paths.

    - browser.ie - Path to Internet Explorer
    - browser.firefox - Path to Firefox
    - browser.chrome - Path to Chrome
    - browser.default - The browser that will be launched. Set this value to ${browser.ie}, ${browser.firefox} or ${browser.chrome}.


VI. Building

Building the Flash Client Development Harness is a two-step process. The first step, preparation, is typically executed
once and then does not need to be repeated until a new build of the CGS environment is deployed. The second step,
building either the standalone or live harness, is repeated frequently, each time the client code or graphics are
changed and need to be tested.

Both steps are executed by running the applicable target of the Ant build.xml file located in the root of the harness
directory. This can be done from the command line or from the IDE.

o Step 1: Preparation

  The preparation step connects to the configured CGS environment and downloads a number of files to a cache directory.
  Once completed the next step of the build process will utilize these files when packing the harness. To execute the
  preparation step, run the "build.prepare" target of the build.xml file.

o Step 2: Building the Harness

  The harness build step involves running either the "build.standalone" or "build.live" targets of the build.xml file,
  depending on how you want server communication to be handled at runtime. In either case, the artifacts of the build
  (the game files, framework files and test page) are packaged in the harness/build/standalone or harness/build/live
  directories. The basic process that occurs in either case is as follows.

    - The build ensures that the preparation step has been completed.
    - The output directory of the harness is destroyed. (build/standalone or build/live)
    - The framework and test page files are copied to the output directory.
    - The properties from either standalone.properties or live.properties are substituted into the test page.
    - The game client is built to a temporary location.
    - The game and framework files are packed into a binary stream file.
    - The game client temporary output is destroyed.

  If the build completes successfully, the harness.html file located in the appropriate output directory can be
  launched.



VII. Running the Harness

Running the harness is as simple as launching the "harness.html" file in the root of the harness's output directory.
For example, to launch the standalone harness, open harness/build/standalone/harness.html.

Alternatively, the Ant build provides two targets for launching the harness; "launch.standalone" and "launch.live".
These targets require that you configure the browser.properties file to point to the correct locations of the
browsers you wish to use.

Finally, a web server can be configured to point to the harness directory, allowing the harness to be launched using
the url. The url to use would depend on the configuration of the web server.

IMPORTANT: When using launching the harness WITHOUT using a web server, the file will run using a the "file" protocol
(file:///). The browser may not allow Flash content to run this unless you allow it. If you experience trouble, see the
Troubleshooting section.

o Start Up Properties

The properties required to start the game are embedded into the page so you do not have to add any query string
parameters to the url to start the game (The embedded values are the ones set in standalone.properties and
live.properties).

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

Adding new responses for a game's specific requests (such as the "SpinReq" of a slot machine) is a simple as adding a
new "responses" node to the map and creating the response files. The map can contain as many "responses" nodes as is
required to service all requests the game might make.

Example:

    <responses request="SpinReq">
        <response id="SpinReq" file="SpinResponse.xml"/>
    </responses>

For convenience, a template standalone response file is included in the web/standalone directory (responsetemplate.xml).


o Response Headers

Each response served by the CGS contains two distinct parts; the message header and the message body. The message header
is never seen by the game but is used by the framework to validate the response. Typically, you should not need to edit
the header (particularly if creating new response files using the responsetemplate.xml) but the header does need to
exist in the response.


o Limitations

Each response carries a balance with it that is handled by the framework and passed to the game frequently. Due to the
fact that standalone mode does not communicate with the server, the balance will always reflect the value of the
<balance> node in the header of each response. It is possible to script basic balance changes this way but it is best
to use live mode to test scenarios that are dependent on balance accuracy.



IX. Troubleshooting

- The harness seems to freeze at startup

    Be sure that you are using the latest version of the Flash Debug Player. An exception is likely being thrown but the
    regular Flash Player hides them. Check your installed version by going to http://kb2.adobe.com/cps/155/tn_15507.html
    and ensuring that it says "Debug Player: Yes".

- A Security Error is thrown at startup

    If you see Error #2148 with a message about trusted local SWF files (below), you need to allow the location in your
    Flash Player Security Settings.

    SecurityError: Error #2148: SWF file <mypath> cannot access local resource flashcasino4/assetStream.bin. Only
    local-with-filesystem and trusted local SWF files may access local resources.
	at flash.net::URLStream/load()

	Go to http://www.macromedia.com/support/documentation/en/flashplayer/help/settings_manager04.html and add the
	harness directory to the "Always trust files in these locations:" list. Alternatively, you may wish to add your
	development directory here.

- Internet Explorer shows nothing on the screen

    Look for a yellow bar at the top of the screen with a message about allowing blocked content. Click it and allow the
    blocked content. This is a security feature to protect the running of local swf files.

- The game begins to load but the in-game error screen appears.

    Click the "+Details" button on the left and note the information listed under Debug. There are numerous errors that
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