/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.test.monarchsun.service;

import   com.cwh.monarchsun.model.GameState;
import   com.cwh.monarchsun.model.PersistentState;
import   com.cwh.monarchsun.model.Player;
import   com.cwh.monarchsun.model.SpinGameRequest;
import com.cwh.monarchsun.service.SlotService;
import mockit.Deencapsulation;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;

public class SlotServiceTest {
    @Test
    public void testResetGameService() {
        final SlotService     slotService     = new SlotService();
        final GameState       gameState       = new GameState();
        final PersistentState persistentState = new PersistentState();
        final Player          player          = new Player();
        final SpinGameRequest spinGameRequest = new SpinGameRequest();

        Deencapsulation.setField(player, "playerId", new Integer(1234));
        slotService.setPlayer(player);
        slotService.setGameState(gameState);
        slotService.setPersistentState(persistentState);

        Deencapsulation.setField(spinGameRequest, "requestName",    "SpinReq");
        Deencapsulation.setField(spinGameRequest, "coinValue",      new BigDecimal("100.00"));
        Deencapsulation.setField(spinGameRequest, "creditWager", new Integer(5));
        slotService.resetGameService(spinGameRequest);

        assertEquals(100.00d, gameState.getCreditValue().doubleValue());
        //assertEquals(5,       gameState.getCreditPerLine().intValue());
        assertEquals(0,       gameState.getCreditWonTotal().intValue());
        assertEquals(GameState.PLAY_STATE.NEW, gameState.getPlayState());
    }
}