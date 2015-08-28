package net.szecc.exercises.salestaxes;

/**
 * <p>A container for <tt>Product</tt> shopping</p>
 * 
 * @author Simone Zecca
 */
public interface Cart {
	
	/**
	 * When all the products have been purchased the <tt>Cart</tt> can be closed.
	 * The operations to complete the purchase are performed by this method.
	 * 
	 * @return a description of the purchase
	 */
	public String close();
	
	public void put(Product product);
	
	public boolean remove(Product product);

}
