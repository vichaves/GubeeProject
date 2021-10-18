package com.example.restservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class GubeeProduct {

	private final String productName;
	private final String description;
	private final List<String> targetMarket = new ArrayList<String>();
	private final List<String> stack = new ArrayList<String>();
	
	public GubeeProduct(String productName, String description, String[] targetMarket, String[] stack) {
		super();
		this.productName = productName;
		this.description = description;
		for(String targMarket : targetMarket) {
			this.targetMarket.add(targMarket);
		}
		for(String tecUtilizada : stack) {
			this.stack.add(tecUtilizada);
		}
	}

	public String getProductName() {
		return productName;
	}



	public String getDescription() {
		return description;
	}



	public List<String> getTargetMarket() {
		return targetMarket;
	}



	public List<String> getStack() {
		return stack;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GubeeProduct [nome=");
		builder.append(productName);
		builder.append(", description=");
		builder.append(description);
		builder.append(", targetMarket=");
		builder.append(targetMarket);
		builder.append(", stack=");
		builder.append(stack);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(stack);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GubeeProduct other = (GubeeProduct) obj;
		return Objects.equals(stack, other.stack);
	}	
	
	
}
