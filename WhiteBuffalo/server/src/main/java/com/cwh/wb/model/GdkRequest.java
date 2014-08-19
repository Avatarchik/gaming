/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.wb.model;

import nu.xom.Node;
import nu.xom.Nodes;

/**
 * This is the parent model object that represents the GDK request.
 * These model objects were quickly written and parsed using XPath.
 * This is not deemed the best path, rather just a simple solution.
 *
 * The final XML document marshaled will be similar to:
 * test/com/cwh/wb/resources/gdkRequest_*.xml
 */
public class GdkRequest {
    private Player          player         = null;
    private GameState       gameState      = null;
    private PersistentState persistentState = null;
    private Profile         profile        = null;
    private RandomPools     randomPools    = null;
    private GameRequest     gameRequest    = null;
    private ReplayGameRequest    		replay    = null;
    
    public ReplayGameRequest getReplay() {
        return replay;
    }

    public GameRequest getGameRequest() {
        return gameRequest;
    }

    public GameState getGameState() {
        return gameState;
    }

    public PersistentState getPersistentState() {
        return persistentState;
    }

    public Player getPlayer() {
        return player;
    }

    public Profile getProfile() {
        return profile;
    }

    public RandomPools getRandomPools() {
        return randomPools;
    }

    public GdkRequest unmarshal(Node eRequest) {
        Nodes nodes = null;
        if((nodes = eRequest.query("gameHeader/player")).size() > 0) {
            this.player          = new Player().unmarshal(nodes.get(0));
        }
        if((nodes = eRequest.query("gameHeader/gameState")).size() > 0) {
            this.gameState       = new GameState().unmarshal(nodes.get(0));
        }
        if((nodes = eRequest.query("gameHeader/persistentState")).size() > 0) {
            this.persistentState = new PersistentState().unmarshal(nodes.get(0));
        }
        if((nodes = eRequest.query("gameHeader/profile")).size() > 0) {
            this.profile         = new Profile().unmarshal(nodes.get(0));
        }
        if((nodes = eRequest.query("gameHeader/randomPools")).size() > 0) {
            this.randomPools     = new RandomPools().unmarshal(nodes.get(0));
        }
        if((nodes = eRequest.query("gameRequest/msgdata")).size() > 0) {
            this.gameRequest     = GameRequest.unmarshalInstance(nodes.get(0));
        }
        if((nodes = eRequest.query("gameRequest/masterReplay")).size() > 0) {
            this.replay     = new ReplayGameRequest().unmarshal(nodes.get(0));
        }      
        return this;
    }
}