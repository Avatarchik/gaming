/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.monarchsun.model;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * This is the parent model object that represents the GDK response. 
 * These model objects were quickly written and parsed using XPath.
 * This is not deemed the best path, rather just a simple solution.
 *
 * The final XML document marshaled will be similar to:
 * test/com/cwh/monarchsun/resources/gdkResponse_*.xml
 */
public class GdkResponse {
    private List<Transaction> transactions    = new LinkedList<Transaction>();
    private GameState         gameState       = null;
    private PersistentState   persistentState = null;
    private GameResponse      gameResponse    = null;
    private String            gameError       = null;
    private boolean           gameInfoOnly    = false;


    public void setGameError(String gameError) {
        this.gameError = gameError;
    }

    public void setGameResponse(GameResponse gameResponse) {
        this.gameResponse = gameResponse;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setPersistentState(PersistentState persistentState) {
        this.persistentState = persistentState;
    }

    public void setGameInfoOnly(boolean gameInfoOnly) {
        this.gameInfoOnly = gameInfoOnly;
    }

    public void addTransactions(List<Transaction> transactions) {
        this.transactions.addAll(transactions);
    }

    public Node marshal() {
        Element eGdkResponse = new Element("gdkResponse");
        eGdkResponse.addAttribute(new Attribute("version", "2"));

        if(this.gameError != null && this.gameError.length() >= 0) {
            // Add GameError node
            Element eGameError   = new Element("gameError");
            eGdkResponse.appendChild(eGameError);
            eGameError.appendChild(this.gameError);
        } else {
            // Add GameState node
            if(this.gameState != null) {
                eGdkResponse.appendChild(this.gameState.marshal());
            }
//            Add PersistentState node
            if(this.persistentState != null) {
                eGdkResponse.appendChild(this.persistentState.marshal());
            }
            // Add GameResponse node
            Element eGameResponse = new Element("gameResponse");
            eGdkResponse.appendChild(eGameResponse);

            // Add Instruction nodes
            Element eInstructions = new Element("instructions");
            eGameResponse.appendChild(eInstructions);
            Element eFinancial    = new Element("financial");
            eInstructions.appendChild(eFinancial);
            for(Transaction aTransaction : this.transactions) {
                eFinancial.appendChild(aTransaction.marshal());
            }
            // Add ResponseToClient nodes
            Element eResponseToClient = new Element("responseToClient");
            eGameResponse.appendChild(eResponseToClient);
            eResponseToClient.appendChild(this.gameResponse.marshal());

            // Check to see if this is a READ ONLY action - Not State change or destructive instructions will be allowed/ignored
            if(this.gameInfoOnly) {
                Element eInfoOnly = new Element("infoOnly");
                eGameResponse.appendChild(eInfoOnly);
            }
        }
        return eGdkResponse;
    }
}