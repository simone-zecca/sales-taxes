package net.szecc.exercises.salestaxes;

import java.math.BigDecimal;

/**
 * <p>Builds up a product, taking care of all the necessary calculations</p>
 * 
 * @author Simone Zecca
 */
public interface ProductFactory {

	/**
	 * Build up a product
	 * 
	 * @param productName
	 * @param quantity
	 * @param amount
	 * @param noSalesTaxType
	 *            <tt>NoSalesTaxEnum</tt> The product category for products that
	 *            are not subject to local taxation
	 * @param importedInfo
	 *            <tt>ImportedEnum</tt> Info about the country of origin of the
	 *            product
	 * 
	 * @see NoSalesTaxEnum
	 * @see ImportedEnum
	 * 
	 * @author szecc
	 */
	public Product make(
			String productName, 
			int quantity, 
			BigDecimal amount, 
			NoSalesTaxEnum noSalesTaxType,
			ImportedEnum importedInfo);

}
