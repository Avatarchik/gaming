/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.model;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Node;

import java.math.BigDecimal;

public class Transaction {
    public static enum TYPE { BET, WIN, RETURN }

    private Integer    playerId = null;
    private TYPE       action   = null;
    private BigDecimal amount   = null;

    public Transaction setAction(TYPE type) {
        this.action = type;
        return this;
    }

    public Transaction setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Transaction setPlayerId(Integer playerId) {
        this.playerId = playerId;
        return this;
    }

    public Node marshal() {
        Element eTransaction = new Element("transaction");
        eTransaction.addAttribute(new Attribute("playerId", this.playerId.toString()));
        eTransaction.addAttribute(new Attribute("action",   this.action.name()));
        eTransaction.addAttribute(new Attribute("amount",   this.amount.toPlainString()));

        return eTransaction;
    }
}