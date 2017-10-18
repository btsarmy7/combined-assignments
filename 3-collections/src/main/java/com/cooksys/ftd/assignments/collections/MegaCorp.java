package com.cooksys.ftd.assignments.collections;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.FatCat;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {
	
	private HashSet<Capitalist> allCapitalists = new HashSet<Capitalist>(); //set of everyone in the MegaCorp
	
	// Map of parent + their set of children
	private Map<FatCat, Set<Capitalist>> parentAndChildren = new  HashMap<FatCat, Set<Capitalist>>();
	
	// set of all parents
	private HashSet<FatCat> allParents = new HashSet<FatCat>(); 
	
	// set of all children
	private Set<Capitalist> children = new HashSet<Capitalist>();
	
	
    /**
     * Adds a given element to the hierarchy.
     * <p>
     * If the given element is already present in the hierarchy,
     * do not add it and return false
     * <p>
     * If the given element has a parent and the parent is not part of the hierarchy,
     * add the parent and then add the given element
     * <p>
     * If the given element has no parent but is a Parent itself,
     * add it to the hierarchy
     * <p>
     * If the given element has no parent and is not a Parent itself,
     * do not add it and return false
     *
     * @param capitalist the element to add to the hierarchy
     * @return true if the element was added successfully, false otherwise
     */
    @Override
    public boolean add(Capitalist capitalist) {
        if(this.allCapitalists.contains(capitalist)) {
        	return false;
        }else if(capitalist.hasParent() && !this.allParents.contains(capitalist.getParent())) {
        	addChildren(capitalist.getParent(), capitalist);
        	this.allParents.add(capitalist.getParent());
        	this.allCapitalists.add(capitalist);
        	return true;
        }else if(!capitalist.hasParent() && this.parentAndChildren.containsKey(capitalist)) {
        	this.allCapitalists.add(capitalist);
        	this.allParents.add((FatCat) capitalist);
        	return true;
        }else {
        	return false;
        }
    }
    
    // add children to capitalist
    public void addChildren(Capitalist parent, Capitalist child) {
    	
    	if(this.parentAndChildren.containsKey(parent)) {
    		this.parentAndChildren.get(parent).add(child);
    			
    	}
    }

    /**
     * @param capitalist the element to search for
     * @return true if the element has been added to the hierarchy, false otherwise
     */
    @Override
    public boolean has(Capitalist capitalist) { 
    	// check for existence of said capitalist in hierarchy of everyone in MegaCorp
        if(allCapitalists.contains(capitalist)) { 
        	return true;
        }else {
        	return false;
        }
    }

    /**
     * @return all elements in the hierarchy,
     * or an empty set if no elements have been added to the hierarchy
     */
    @Override
    public Set<Capitalist> getElements() {
    	if(this.allCapitalists.isEmpty()) {
    		return null;
    	}else {
    		return this.allCapitalists;
    	}
    }

    /**
     * @return all parent elements in the hierarchy,
     * or an empty set if no parents have been added to the hierarchy
     */
    @Override
    public Set<FatCat> getParents() {
        if(this.allParents.isEmpty()) {
        	return null;
        }else {
        	return this.allParents;
        }
    }

    /**
     * @param fatCat the parent whose children need to be returned
     * @return all elements in the hierarchy that have the given parent as a direct parent,
     * or an empty set if the parent is not present in the hierarchy or if there are no children
     * for the given parent
     */
    @Override
    public Set<Capitalist> getChildren(FatCat fatCat) {
    	if(this.allParents.isEmpty() || this.parentAndChildren.isEmpty()) {
    		return null;
    	}else if(!this.allParents.contains(fatCat) || !this.parentAndChildren.containsKey(fatCat)) {
        	return null;
        }else {
        	return this.parentAndChildren.get(fatCat); // return the fatCat's set of children
        }
    }

    /**
     * @return a map in which the keys represent the parent elements in the hierarchy,
     * and the each value is a set of the direct children of the associate parent, or an
     * empty map if the hierarchy is empty.
     */
    @Override
    public Map<FatCat, Set<Capitalist>> getHierarchy() {
        if(this.parentAndChildren.isEmpty()) {
        	return null;
        }else {
        	return this.parentAndChildren;
        }
    }

    /**
     * @param capitalist
     * @return the parent chain of the given element, starting with its direct parent,
     * then its parent's parent, etc, or an empty list if the given element has no parent
     * or if its parent is not in the hierarchy
     */
    @Override
    public List<FatCat> getParentChain(Capitalist capitalist) {
    	ArrayList<FatCat> chain = new ArrayList<FatCat>();
    	Capitalist c = capitalist;
    	
        if(this.allParents.isEmpty()) {
        	return null;
        } else {
        	while(c.hasParent()) {
        		chain.add((FatCat) c);
        		c = c.getParent();
        	}
        	return chain;
        }
    }
}
