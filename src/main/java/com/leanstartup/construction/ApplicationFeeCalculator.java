package com.leanstartup.construction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApplicationFeeCalculator {

	private List<FeeAccessed> feesAccessed;
	
	public ApplicationFeeCalculator(List<FeeAccessed> feesAccessed){
		if (feesAccessed == null){
			 feesAccessed = new ArrayList<FeeAccessed>();
		}
		Collections.sort(feesAccessed);
		this.feesAccessed = feesAccessed;
	}
	
	public BigDecimal calculateFee(BigDecimal invoiceAmount){
		
		BigDecimal totalFee = BigDecimal.ZERO;
		
		BigDecimal amountRemaining = invoiceAmount;
		
		for (FeeAccessed feeAccessed: feesAccessed){
			if (invoiceAmount.compareTo(feeAccessed.getStartRange()) > 0){
				totalFee = calculateFee(amountRemaining, feeAccessed.getStartRange(), feeAccessed.getPercentCharged(), totalFee);
				amountRemaining = feeAccessed.getStartRange();
			}
		}
		
		
		return totalFee;
	}

	public BigDecimal calculateFee(BigDecimal invoiceAmount, BigDecimal costBasis){
		
		BigDecimal totalFee = BigDecimal.ZERO;
		
		BigDecimal feeForInvoicePlusCostBasis =  calculateFee(invoiceAmount.add(costBasis));
		BigDecimal feeForCostBasis =  calculateFee(costBasis);
		
		totalFee = feeForInvoicePlusCostBasis.subtract(feeForCostBasis);
					
		return totalFee;
	}
	
	private BigDecimal calculateFee(BigDecimal amount, BigDecimal range, BigDecimal percentCharged, BigDecimal feeAccessed){
		
		BigDecimal amountAboveRange = amount.subtract(range);
		return amountAboveRange.multiply(percentCharged).add(feeAccessed);
		
	}
	
	
}
