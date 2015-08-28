package net.szecc.exercises.salestaxes;

import java.math.BigDecimal;

/**
 * <p>A Bean representing a good that can be purchased</p>
 * 
 * @author Simone Zecca
 */
public class Product {
	
	private String name;
	private int quantity = 0;
	private BigDecimal amount;
	private BigDecimal taxes;
	private NoSalesTaxEnum noSalesTaxReason = null;
	private ImportedEnum importedInfo = null;
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append("\t[[");
		sb.append("name:"+name);
		sb.append("][");
		sb.append("quantity:"+quantity);
		sb.append("][");
		sb.append("amount:"+amount);
		sb.append("][");
		sb.append("taxes:"+taxes);
		sb.append("][");
		sb.append("noSalesTaxReason:"+noSalesTaxReason);
		sb.append("][");
		sb.append("importedInfo:"+importedInfo);
		sb.append("]]");
		return sb.toString();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getTaxes() {
		return taxes;
	}
	public void setTaxes(BigDecimal taxes) {
		this.taxes = taxes;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public NoSalesTaxEnum getNoSalesTaxReason() {
		return noSalesTaxReason;
	}
	public void setNoSalesTaxReason(NoSalesTaxEnum noSalesTaxReason) {
		this.noSalesTaxReason = noSalesTaxReason;
	}
	public ImportedEnum getImportedInfo() {
		return importedInfo;
	}
	public void setImportedInfo(ImportedEnum importedInfo) {
		this.importedInfo = importedInfo;
	}	
}
