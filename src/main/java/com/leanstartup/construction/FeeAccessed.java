package com.leanstartup.construction;

import java.math.BigDecimal;

public class FeeAccessed implements Comparable<FeeAccessed>{

	private BigDecimal percentCharged;
	private BigDecimal startRange;
	
	public BigDecimal getPercentCharged() {
		return percentCharged;
	}
	
	public void setPercentCharged(BigDecimal percentCharged) {
		this.percentCharged = percentCharged;
	}
	
	public BigDecimal getStartRange() {
		return startRange;
	}
	
	public void setStartRange(BigDecimal startRange) {
		this.startRange = startRange;
	}
	
	public int compareTo(FeeAccessed o) {
		return o.getStartRange().compareTo( this.startRange);
	}
	
	
}
