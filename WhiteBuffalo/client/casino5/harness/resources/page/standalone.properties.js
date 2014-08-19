var properties = { harness:"standalone" };

// The displayable name of the game. Appears on the loading screen.
// In Standalone mode: Value is required.
// In Live mode: Value is required.
properties.displayName = "{displayName}";

// The start game name of the game as defined in the DB.
// In Standalone mode: Value is required to match the game name defined in the profile xml.
// In Live mode: Value is required to be the start game name from the DB.
properties.gameName = "{gameName}";

// The 2-character language code to use when playing the game.
// In Standalone mode: Value is required.
// In Live mode: Value is required.
properties.lang = "{lang}";

// The 3-character account code to use.
// In Standalone mode: Value is only used for currency formatting purposes.
// In Live mode: Value is required and is used to access the funded account (as well as currency formatting).
properties.account = "{account}";

// Indicates if the game should resume an unfinished game ("1") or start a new one ("0").
// In Standalone mode: Value determines which way the game is started.
// In Live mode: Value determines which way the game is started. To use value "1", an unfinished game must exist on the server.
properties.resumedGame = "{resumedGame}";

// Indicates if the game is a full-size (false) or mini (true) game client.
// In Standalone mode: Value specifies the mode to operate the framework in.
// In Live mode: Value specifies the mode to operate the framework in.
properties.mode = "{mode}";

// Additional data expected tp be returned by the data service
// Leave the value blank if no additional is data expected.
// In most cases, this value is not used.
properties.data = "{data}";

// URL to the external game file to be loaded.
// This is only relevant when build egi games.
// Leave the value blank if this is not an egi game.
properties.egiURL = "{egiURL}";

// External game id (vendor specific) of the game.
// This is only relevant when build egi games.
// Leave the value blank if this is not an egi game.
properties.egiExtGameId = "{egiExtGameId}";