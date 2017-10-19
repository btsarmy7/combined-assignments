package com.cooksys.ftd.assignments.collections;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.FatCat;
import com.cooksys.ftd.assignments.collections.model.WageSlave;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {
	
	private HashSet<Capitalist> allCapitalists = new HashSet<Capitalist>(); //set of everyone in the MegaCorp
	
	// Map of parent + their set of children
	private Map<FatCat, Set<Capitalist>> parentAndChildren = new  HashMap<FatCat, Set<Capitalist>>();
	
	// set of all parents
	private HashSet<FatCat> allParents = new HashSet<FatCat>(); 
	
	
	
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
    	if(capitalist == null) {
    		return false; 
    	}else if(!capitalist.hasParent() && capitalist instanceof WageSlave) {
    		return false; 
    	}else if(!capitalist.hasParent() && capitalist instanceof FatCat) { 
        	if(this.allCapitalists.contains(capitalist)) {
                return false;
        	}
        	this.allCapitalists.add(capitalist);
        	this.allParents.add((FatCat) capitalist);
        	if(this.parentAndChildren.containsKey(capitalist)) {
        		this.parentAndChildren.put((FatCat) capitalist, this.parentAndChildren.get(capitalist));
        	}else {
        		addChildren(capitalist, null);
        	}
            return true;
    	}else if(!this.allCapitalists.contains(capitalist)) {
    		this.allCapitalists.add(capitalist);
    		return add(capitalist);
    	}else if(!this.allParents.contains(capitalist) && capitalist instanceof FatCat) {
    		this.allParents.add((FatCat) capitalist);
    		if(!this.parentAndChildren.containsKey(capitalist)) {
    			addChildren((FatCat) capitalist, null);
    		}
    		return add(capitalist); 
        }else if(capitalist.hasParent()) { 
        	addChildren(capitalist.getParent(), capitalist);
        	return add(capitalist.getParent());  
    	}else {
        	return false;
        }
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allCapitalists == null) ? 0 : allCapitalists.hashCode());
		result = prime * result + ((allParents == null) ? 0 : allParents.hashCode());
		result = prime * result + ((parentAndChildren == null) ? 0 : parentAndChildren.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MegaCorp other = (MegaCorp) obj;
		if (allCapitalists == null) {
			if (other.allCapitalists != null)
				return false;
		} else if (!allCapitalists.equals(other.allCapitalists))
			return false;
		if (allParents == null) {
			if (other.allParents != null)
				return false;
		} else if (!allParents.equals(other.allParents))
			return false;
		if (parentAndChildren == null) {
			if (other.parentAndChildren != null)
				return false;
		} else if (!parentAndChildren.equals(other.parentAndChildren))
			return false;
		return true;
	}

	// add children to capitalist
    public void addChildren(Capitalist parent, Capitalist child) {
    	if(parent == null) {
    		// do nothing
    	}else if(this.parentAndChildren.containsKey(parent)) {
    		this.parentAndChildren.get(parent).add(child);	
    	}else {
    		this.parentAndChildren.put((FatCat) parent, children(child));
    	}
    }
    
    // create a set of children
    public Set<Capitalist> children(Capitalist child){
    	Set<Capitalist> kids = new HashSet<Capitalist>();
    	if(child != null) {
    		kids.add(child);
    	}
		return kids;
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
    	Set<Capitalist> elements = new HashSet<Capitalist>();
    	if(this.allCapitalists == null) {
    		return elements;
    	}else {
    		for(Capitalist c : this.allCapitalists) {
        		elements.add(c);
        	}
        	return elements;
    	}
    	
    }

    /**
     * @return all parent elements in the hierarchy,
     * or an empty set if no parents have been added to the hierarchy
     */
    @Override
    public Set<FatCat> getParents() {

    	Set<FatCat> parents = new HashSet<FatCat>();
    	if(this.allParents == null) {
    		return parents;
    	}else {
    		for(FatCat p : this.allParents) {
        		parents.add(p);
        	}
        	return parents;
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
    	
    	Set<Capitalist> children = new HashSet<Capitalist>();
    	if(!this.allParents.contains(fatCat) || !this.parentAndChildren.containsKey(fatCat)) {
    		return children;
        }else {
        	for(Capitalist c : this.parentAndChildren.get(fatCat)) {
        		children.add(c);
        	}
        	return children; // return the fatCat's set of children
        }
    }

    /**
     * @return a map in which the keys represent the parent elements in the hierarchy,
     * and the each value is a set of the direct children of the associate parent, or an
     * empty map if the hierarchy is empty.
     */
    @Override
    public Map<FatCat, Set<Capitalist>> getHierarchy() { 
    	Map<FatCat, Set<Capitalist>> hierarchy = new HashMap<FatCat, Set<Capitalist>>();
    	if(this.parentAndChildren == null) {
    		return hierarchy;
    	}else {
    		
    		for(FatCat fc : this.allParents) {
    			hierarchy.put(fc, getChildren(fc));
    		}
        	return hierarchy;
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
        if(!this.allCapitalists.contains(capitalist) || !this.allCapitalists.contains(capitalist.getParent()) 
        		&& !capitalist.hasParent()) {
        	return chain;
        } else {
        	//chain.add((FatCat) c);
        	while(c.hasParent()) {
        		c = c.getParent();
        		chain.add((FatCat) c);
        	}
        	return chain;
        }
    }
}
