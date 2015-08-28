package net.szecc.exercises.salestaxes;

import java.math.BigDecimal;

/**
 * <p>Implementation of the <tt>ProductFactory</tt> interface that 
 * perform Tax calculation over the requested <tt>Product</tt></p>
 * 
 *  @see ProductFactory
 *  @see Product
 *  
 *  @author Simone Zecca
 */
public class TaxedProductFactory implements ProductFactory {

	private BigDecimal salesTaxRate = null;
	private BigDecimal importTaxRate = null;
	
	public Product make(
			String productName, 
			int quantity, 
			BigDecimal amount, 
			NoSalesTaxEnum noSalesTaxType,
			ImportedEnum importedInfo	) {

		Product product = new Product();
		product.setName(productName);
		product.setQuantity(quantity);
		
		//more than 2 decimals is not accepted
		if (amount.scale() > 2) {
			throw new RuntimeException(
					"Unexpected amount. Only 2 decimal figures can be accepted:" + 
							amount.toString());
		}

		product.setAmount(amount.setScale(2));
		product.setNoSalesTaxReason(noSalesTaxType);
		product.setImportedInfo(importedInfo);

		BigDecimal salesFee = BigDecimal.ZERO;
		if (product.getNoSalesTaxReason() == null && salesTaxRate != null) {
			salesFee = roundUp005(product.getAmount().multiply(salesTaxRate));
		}

		BigDecimal importFee = BigDecimal.ZERO;
		if (product.getImportedInfo() != null && importTaxRate != null) {
			importFee = roundUp005(product.getAmount().multiply(importTaxRate));
		}

		product.setTaxes(salesFee.add(importFee));

		return product;
	}

	/**
	 * RoundsUp to the nearest 0.05
	 * 0.05 == 1/20. So get the nearest number dividing by 1/20 + round UP, then multiply by 1/20  
	 * */
	private BigDecimal roundUp005(BigDecimal tax) {
		BigDecimal roundTax = new BigDecimal(Math.ceil(tax.doubleValue() * 20) / 20);
		//remove possibly floating point imprecision
		return roundTax.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void setSalesTaxRate(Double salesTaxRate) {
		this.salesTaxRate = new BigDecimal(salesTaxRate);
	}

	public void setImportTaxRate(BigDecimal importTaxRate) {
		this.importTaxRate = importTaxRate;
	}
}
