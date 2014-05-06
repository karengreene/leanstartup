package com.leanstartup.construction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ApplicationFeeCalculatorTest {

	private ApplicationFeeCalculator feeCalculator;
	private static BigDecimal feeBelowOneHundredThousand = new BigDecimal(".20");
	private static BigDecimal feeAboveOneHundredThousand = new BigDecimal(".10");
	private static BigDecimal feeAboveFiveHundredThousand = new BigDecimal(".05");
	private static BigDecimal oneHundredThousand = new BigDecimal("100000");
	private static BigDecimal fiveHundredThousand = new BigDecimal("500000");
	
	@Before
	public void init(){
		List<FeeAccessed> feesAccessed = new ArrayList<FeeAccessed>();
		FeeAccessed fee1 = new FeeAccessed();
		fee1.setPercentCharged(feeAboveFiveHundredThousand);
		fee1.setStartRange(fiveHundredThousand);
		feesAccessed.add(fee1);
		
		FeeAccessed fee2 = new FeeAccessed();
		fee2.setPercentCharged(feeAboveOneHundredThousand);
		fee2.setStartRange(oneHundredThousand);
		feesAccessed.add(fee2);
		
		FeeAccessed fee3 = new FeeAccessed();
		fee3.setPercentCharged(feeBelowOneHundredThousand);
		fee3.setStartRange(BigDecimal.ZERO);
		feesAccessed.add(fee3);
		feeCalculator = new ApplicationFeeCalculator(feesAccessed);
	}
	
	@Test
	public void testCalculateFeeAbove100000(){
		
		BigDecimal feeAcessed = feeCalculator.calculateFee(new BigDecimal("200000"));
		
		Assert.assertEquals(new BigDecimal("30000.00"),feeAcessed);
	}
	
	@Test
	public void testCalculateFeeAbove500000(){
		
		BigDecimal feeAcessed = feeCalculator.calculateFee(new BigDecimal("600000"));
		
		Assert.assertEquals(new BigDecimal("65000.00"),feeAcessed);
	}
	
	@Test
	public void testCalculateFeeBelow100000(){
		
		BigDecimal feeAcessed = feeCalculator.calculateFee(new BigDecimal("50000"));
		
		Assert.assertEquals(new BigDecimal("10000.00"),feeAcessed);
	}
	
	@Test
	public void testCalculateAt100000(){
		
		BigDecimal feeAcessed = feeCalculator.calculateFee(new BigDecimal("100000"));
		
		Assert.assertEquals(new BigDecimal("20000.00"),feeAcessed);
	}
	
	@Test 
	public void testCalculateFeeAt200000WithCostBasis() {
		BigDecimal feeAcessed = feeCalculator.calculateFee(new BigDecimal("200000"), new BigDecimal("400000"));
		
		Assert.assertEquals(new BigDecimal("15000.00"),feeAcessed);
	}
}
