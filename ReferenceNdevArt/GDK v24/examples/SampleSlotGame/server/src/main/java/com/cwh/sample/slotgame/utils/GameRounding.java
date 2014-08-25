/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GameRounding {
    /**
     * This method will round a given amount to the correct scale (From the ThreadLocal session).
     *
     * @param amount   The amount to be rounded and scaled.
     * @param rounding The Round rule.
     * @return BigDecimal. The result of the rounding and scaling.
     */
    public static BigDecimal roundToCurrencyScale(BigDecimal amount, RoundingMode rounding) {
        final Integer currScale = new Integer(ThreadSession.getCurrentSession().get(ThreadSession.KEYS.CURR_SCALE));
        return amount.setScale(currScale, rounding);
    }

    /**
     * This method will shift the decimal 2 places to the right, to convert any currency (with 2 decimals)
     * into an integer.
     *
     * @param  amount  The amount to be shifted.
     * @return Integer. Representation of the credit value.
     */
    public static Integer shiftForCredits(BigDecimal amount) {
        return amount.movePointRight(2).intValueExact();
    }

    /**
     * This method will shift the decimal 2 places to the left, to convert any credit value into
     * a currency value with 2 decimal places.
     *
     * @param  amount The amount to be shifted.
     * @return BigDecimal. Representation of the currency value.
     */
    public static BigDecimal shiftForCurrency(Integer amount) {
        return new BigDecimal(amount).movePointLeft(2).setScale(2, RoundingMode.HALF_UP);
    }
}