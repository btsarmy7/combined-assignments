package com.cooksys.ftd.assignments.collections.model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@SuppressWarnings("unused")
public class WageSlave implements Capitalist {
	
	private String name;
	private int salary;
	private FatCat owner;
	
    public WageSlave(String name, int salary) {
    	
        this.name = name;
        this.salary = salary;
    }

    public WageSlave(String name, int salary, FatCat owner) {
    	
        this.salary = salary;
        this.name = name;
        this.owner = owner;
    }

    /**
     * @return the name of the capitalist
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @return the salary of the capitalist, in dollars
     */
    @Override
    public int getSalary() {
        return this.salary;
    }

    /**
     * @return true if this element has a parent, or false otherwise
     */
    @Override
    public boolean hasParent() {
        if(!this.owner.equals(null)) {
        	return true;
        }else {
        	return false;
        }
    }

    /**
     * @return the parent of this element, or null if this represents the top of a hierarchy
     */
    @Override
    public FatCat getParent() {
        if(this.hasParent()) {
        	return owner;
        }else {
        	return null;
        }
    }
}
