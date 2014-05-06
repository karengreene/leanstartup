package com.leanstartup.construction;

import java.math.BigDecimal;

public class ApplicationFeeCalculator {

	private static BigDecimal feeBelowOneHundredThousand = new BigDecimal(".20");
	private static BigDecimal feeAboveOneHundredThousand = new BigDecimal(".10");
	private static BigDecimal feeAboveFiveHundredThousand = new BigDecimal(".05");
	private static BigDecimal oneHundredThousand = new BigDecimal("100000");
	private static BigDecimal fiveHundredThousand = new BigDecimal("500000");
	
	
	public static BigDecimal calculateFee(BigDecimal invoiceAmount){
		
		BigDecimal feeAccessed = BigDecimal.ZERO;
		
		BigDecimal amountRemaining = invoiceAmount;
		
		if (amountRemaining.compareTo(fiveHundredThousand) > 0 ){
			feeAccessed = calculateFee(amountRemaining, fiveHundredThousand, feeAboveFiveHundredThousand, feeAccessed);
			amountRemaining = fiveHundredThousand;
		}
		if (amountRemaining.compareTo(oneHundredThousand) > 0 ){
			feeAccessed = calculateFee(amountRemaining, oneHundredThousand, feeAboveOneHundredThousand, feeAccessed);
			amountRemaining = oneHundredThousand;
		}
		
			
		feeAccessed  = amountRemaining.multiply(feeBelowOneHundredThousand).add(feeAccessed);
		
		
		
		return feeAccessed;
	}
	
	private static BigDecimal calculateFee(BigDecimal amount, BigDecimal range, BigDecimal percentCharged, BigDecimal feeAccessed){
		
		BigDecimal amountAboveRange = amount.subtract(range);
		return amountAboveRange.multiply(percentCharged).add(feeAccessed);
		
	}
	
	
}
