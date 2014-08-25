/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.utils;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static junit.framework.Assert.assertEquals;

public class GameRoundingTest {
    @Test
    public void testRoundToCurrency_ScaleValueBelowHalf() {
        BigDecimal rawValue = new BigDecimal("13333.33333333333");

        ThreadSession.getCurrentSession().add(ThreadSession.KEYS.CURR_SCALE, "-2");
        BigDecimal aValue = GameRounding.roundToCurrencyScale(rawValue, RoundingMode.HALF_UP);
        assertEquals(13300d, aValue.doubleValue());

        ThreadSession.getCurrentSession().add(ThreadSession.KEYS.CURR_SCALE, "0");
        BigDecimal bValue = GameRounding.roundToCurrencyScale(rawValue, RoundingMode.HALF_UP);
        assertEquals(13333d, bValue.doubleValue());

        ThreadSession.getCurrentSession().add(ThreadSession.KEYS.CURR_SCALE, "2");
        BigDecimal cValue = GameRounding.roundToCurrencyScale(rawValue, RoundingMode.HALF_UP);
        assertEquals(13333.33d, cValue.doubleValue());
    }

    @Test
    public void testRoundToCurrencyScale_ValueAboveHalf() {
        BigDecimal rawValue = new BigDecimal("13333.66666666");

        ThreadSession.getCurrentSession().add(ThreadSession.KEYS.CURR_SCALE, "-2");
        BigDecimal aValue = GameRounding.roundToCurrencyScale(rawValue, RoundingMode.HALF_UP);
        assertEquals(13300d, aValue.doubleValue());

        ThreadSession.getCurrentSession().add(ThreadSession.KEYS.CURR_SCALE, "0");
        BigDecimal bValue = GameRounding.roundToCurrencyScale(rawValue, RoundingMode.HALF_UP);
        assertEquals(13334d, bValue.doubleValue());

        ThreadSession.getCurrentSession().add(ThreadSession.KEYS.CURR_SCALE, "2");
        BigDecimal cValue = GameRounding.roundToCurrencyScale(rawValue, RoundingMode.HALF_UP);
        assertEquals(13333.67d, cValue.doubleValue());
    }

    @Test
    public void testShifting_Right() {
        assertEquals(13366, GameRounding.shiftForCredits(new BigDecimal("133.66")).intValue());
    }

    @Test
    public void testShifting_Left() {
        assertEquals(133.66d, GameRounding.shiftForCurrency(13366).doubleValue());
    }
}