/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.wb.model;

import nu.xom.Node;

public abstract class GameResponse {        
    protected String        requestName   = null;
    protected RunningResult runningResult = null;
    protected SummaryResult summaryResult = null;

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public void setSummaryResult(SummaryResult summaryResult) {
        this.summaryResult = summaryResult;
    }

    public RunningResult getRunningResult() {
        return runningResult;
    }

    public void setRunningResult(RunningResult runningResult) {
        this.runningResult = runningResult;
    }

    public abstract Node marshal();
}