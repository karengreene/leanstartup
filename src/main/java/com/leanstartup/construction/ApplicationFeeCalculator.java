package com.leanstartup.construction;

import java.math.BigDecimal;

public class ApplicationFeeCalculator {

	private static BigDecimal feeBelowOneHundredThousand = new BigDecimal(".20");
	private static BigDecimal feeAboveOneHundredThousand = new BigDecimal(".10");
	private static BigDecimal oneHundredThousand = new BigDecimal("100000");
	
	public static BigDecimal calculateFee(BigDecimal invoiceAmount){
		
		BigDecimal feeAccessed = null;
		
		if (invoiceAmount.compareTo(oneHundredThousand) > 0 ){
			BigDecimal amountAboveOneHundredThousand = invoiceAmount.subtract(oneHundredThousand);
			BigDecimal amountChargedOverOneHundredThousand  = amountAboveOneHundredThousand.multiply(feeAboveOneHundredThousand);
			feeAccessed = oneHundredThousand.multiply(feeBelowOneHundredThousand).add(amountChargedOverOneHundredThousand);
		}
		else{
			feeAccessed = invoiceAmount.multiply(feeBelowOneHundredThousand);
		}
		
		
		return feeAccessed;
	}
}
