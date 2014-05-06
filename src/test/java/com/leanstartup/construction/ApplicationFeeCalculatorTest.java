package com.leanstartup.construction;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class ApplicationFeeCalculatorTest {

	@Test
	public void testCalculateFeeAbove100000(){
		
		BigDecimal feeAcessed = ApplicationFeeCalculator.calculateFee(new BigDecimal("200000"));
		
		Assert.assertEquals(new BigDecimal("30000.00"),feeAcessed);
	}
	
	@Test
	public void testCalculateFeeAbove500000(){
		
		BigDecimal feeAcessed = ApplicationFeeCalculator.calculateFee(new BigDecimal("600000"));
		
		Assert.assertEquals(new BigDecimal("65000.00"),feeAcessed);
	}
	
	@Test
	public void testCalculateFeeBelow100000(){
		
		BigDecimal feeAcessed = ApplicationFeeCalculator.calculateFee(new BigDecimal("50000"));
		
		Assert.assertEquals(new BigDecimal("10000.00"),feeAcessed);
	}
	
	@Test
	public void testCalculateAt100000(){
		
		BigDecimal feeAcessed = ApplicationFeeCalculator.calculateFee(new BigDecimal("100000"));
		
		Assert.assertEquals(new BigDecimal("20000.00"),feeAcessed);
	}
}
