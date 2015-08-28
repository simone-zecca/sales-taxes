package net.szecc.exercises.salestaxes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Implementation of the <tt>Cart</tt> interface that 
 * performs Total amount and Tax calculation over the 
 * purchased <tt>Product</tt> instances.</p>
 * 
 * <p>A Bill with informations about each purchased product plus
 * the total amount of taxes
 * the total amount to be paid
 * is returned when the cart is closed.</p>
 * 
 *  @see Cart
 *  @see Product
 *  
 *  @author Simone Zecca
 */
public class BillingCart implements Cart {
	
	private List<Product> products = null;
	
	//Current platform's default line separator 
	private static String CRLF = System.getProperty("line.separator");
	
	public BillingCart() {
		products = new ArrayList<Product>();
	}
	
	public void put(Product product) {
		if (product != null) {
			products.add(product);
		}
	}
	
	public boolean remove(Product product) {
		if (product != null) {
			return products.remove(product);
		}
		return false;
	}
		
	private BigDecimal getTaxes() {
		BigDecimal taxes = BigDecimal.ZERO;
		for (Product p : products) {
			taxes = taxes.add(p.getTaxes());
		}
		return taxes;
	}
	
	private BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (Product p : products) {
			total = total.add(p.getTaxes());
			total = total.add(p.getAmount());
		}
		return total;
	}
	
	/**
	 * <p>Close the cart and return a bill containing:<br>
	 * - informations about each purchased product:<br>
	 * - the total amount of taxes<br>
	 * - the total amount to be paid<br></p>
	 * 
	 *  @see Cart
	 *  @see Product
	 *  
	 *  @author Simone Zecca
	 */
	public String close() {
		if (products.size() == 0) {
			throw new RuntimeException("No products in the cart");
		}
		
		StringBuilder sb = new StringBuilder();
		for (Product p : products) {
			sb.append( String.format("%-3s", p.getQuantity()) );
			sb.append(" ");
			sb.append(p.getName());
			sb.append(": ");
			sb.append( p.getAmount().add(p.getTaxes()) );
			sb.append(CRLF);
		}
		sb.append("Sales Taxes: " + getTaxes());
		sb.append(CRLF);
		sb.append("Total: " + getTotal());
		sb.append(CRLF);
		
		return sb.toString();		
	}
	
}
