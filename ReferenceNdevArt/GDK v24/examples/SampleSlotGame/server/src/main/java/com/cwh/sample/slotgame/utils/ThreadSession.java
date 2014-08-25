/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * This class will maintain thread parameters that are stored in ThreadLocal.
 * Currently this is used for Currency and CurrencyScale. For rounding.
 */
public class ThreadSession {
    public static enum KEYS{CURR_CODE, CURR_SCALE}

    private static final ThreadLocal<ThreadSession> threadLocal = new ThreadLocal<ThreadSession>() {
        @Override
        protected ThreadSession initialValue() {
            return new ThreadSession();
        }
    };
    private final Map<KEYS, String> sessionMap  = new HashMap<KEYS, String>();

    public static ThreadSession getCurrentSession() {
        return threadLocal.get();
    }

    public void add(KEYS key, String value) {
        this.sessionMap.put(key, value);
    }

    public String get(KEYS key) {
        return this.sessionMap.get(key);
    }

    public void reset() {
        this.sessionMap.clear();
    }
}