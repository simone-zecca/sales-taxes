package net.szecc.exercises.salestaxes;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.ComparisonFailure;
import junit.framework.TestCase;

/**
 * Unit tests For Sales Taxes App
 * 
 */
public class SalesTaxesTest extends TestCase {

	private static final Logger LOG = Logger.getLogger(SalesTaxesTest.class);

	private ApplicationContext context = null;
	private ProductFactory productFactory = null;
	
	//Current platform's default line separator 
	private static String CRLF = System.getProperty("line.separator");

	protected void setUp() {
		context = new ClassPathXmlApplicationContext("context.xml");
		productFactory = (ProductFactory) context.getBean("productFactory");
	}

	public void test1() {
		LOG.info("Starting test1...");
		String expectedBill = 
				"1   book: 12.49" + CRLF + 
				"1   music CD: 16.49" + CRLF + 
				"1   chocolate bar: 0.85" + CRLF + 
				"Sales Taxes: 1.50" + CRLF + 
				"Total: 29.83" + CRLF;

		Cart cart = new BillingCart();

		Product p1 = productFactory.make("book", 1, BigDecimal.valueOf(12.49), NoSalesTaxEnum.BOOKS, null);
		Product p2 = productFactory.make("music CD", 1, BigDecimal.valueOf(14.99), null, null);
		Product p3 = productFactory.make("chocolate bar", 1, BigDecimal.valueOf(0.85), NoSalesTaxEnum.FOOD, null);

		cart.put(p1);
		cart.put(p2);
		cart.put(p3);

		String actualBill = cart.close();
		LOG.info("Result:" + CRLF + CRLF + actualBill);

		try {
			assertEquals(expectedBill, actualBill);
		} catch(ComparisonFailure e) {
			LOG.error("FAILED!", e);
			throw e;
		}
		LOG.info("test1 PASSED");
	}
	
	public void test2() {
		LOG.info("Starting test2...");
		String expectedBill = 
				"1   imported box of chocolates: 10.50" + CRLF +
				"1   imported bottle of perfume: 54.65" + CRLF +
				"Sales Taxes: 7.65" + CRLF +
				"Total: 65.15" + CRLF;


		Cart cart = new BillingCart();

		Product p1 = productFactory.make("imported box of chocolates", 1, BigDecimal.valueOf(10.00), NoSalesTaxEnum.FOOD, ImportedEnum.GENERIC_FOREIGN_COUNTRY);
		Product p2 = productFactory.make("imported bottle of perfume", 1, BigDecimal.valueOf(47.50), null, ImportedEnum.GENERIC_FOREIGN_COUNTRY);

		cart.put(p1);
		cart.put(p2);

		String actualBill = cart.close();
		LOG.info("Result:" + CRLF + CRLF + actualBill);

		try {
			assertEquals(expectedBill, actualBill);
		} catch(ComparisonFailure e) {
			LOG.error("FAILED!", e);
			throw e;
		}
		LOG.info("test2 PASSED");
	}
	
	public void test3() {
		LOG.info("Starting test3...");
		String expectedBill = 
				"1   imported bottle of perfume: 32.19" + CRLF +
				"1   bottle of perfume: 20.89" 			+ CRLF +
				"1   packet of headache pills: 9.75" 	+ CRLF +
				"1   imported box of chocolates: 11.85" + CRLF +
				"Sales Taxes: 6.70" 					+ CRLF +
				"Total: 74.68" 							+ CRLF;


		Cart cart = new BillingCart();

		Product p1 = productFactory.make("imported bottle of perfume", 1, BigDecimal.valueOf(27.99), null, ImportedEnum.GENERIC_FOREIGN_COUNTRY);
		Product p2 = productFactory.make("bottle of perfume", 1, BigDecimal.valueOf(18.99), null, null);
		Product p3 = productFactory.make("packet of headache pills", 1, BigDecimal.valueOf(9.75), NoSalesTaxEnum.MEDICAL, null);
		Product p4 = productFactory.make("imported box of chocolates", 1, BigDecimal.valueOf(11.25), NoSalesTaxEnum.FOOD, ImportedEnum.GENERIC_FOREIGN_COUNTRY);

		cart.put(p1);
		cart.put(p2);
		cart.put(p3);
		cart.put(p4);

		String actualBill = cart.close();
		LOG.info("Result:" + CRLF + CRLF + actualBill);
		
		try {
			assertEquals(expectedBill, actualBill);
		} catch(ComparisonFailure e) {
			LOG.error("FAILED!", e);
			throw e;
		}
		LOG.info("test3 PASSED");
	}
}
