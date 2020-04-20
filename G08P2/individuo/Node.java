package individuo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Node<T> {
    private T value;
    private List<Node<T>> children;
    private Node<T> parent;

    public Node(T data) {
        value = data;
        children = new ArrayList<Node<T>>();
        parent = null;
    }
    
    public T getValue()
    {
    	return value;
    }

    public void addChildren(Node<T> newChild){
    	if (newChild.getParent() != null)
    	{
    		System.out.println("ERROR: Children already has a parent. Could not be added to node.");
    		return;
    	}
    	newChild.setParent(this);
    	children.add(newChild);
    }
    
    public void removeChildren(int i)
    {
    	if (i > 0 && i < children.size())
    		children.remove(i);
    }
    
    public void removeAllChildren()
    {
    	while(children.size() > 0)
    	{
    		children.remove(0);
    	}
    }
    
    public int getNumChildren()
    {
    	return children.size();
    }
    
    public Node<T> getChild(int i)
    {
    	if (i > 0 && i < children.size())
    	{
    		return children.get(i);
    	}
    	return null;
    }
    
    public Node<T> getParent()
    {
    	return parent;
    }
    
    public void setParent(Node<T> parent)
    {
    	this.parent = parent;
    }
    
    public int getDepth()
    {
    	int numChild = this.getNumChildren();
    	if (numChild == 0)
    	{
    		return 1;
    	}
    	else {  
    		int max = 1;
    		for (int i = 0; i < numChild; i++)
    		{
    			int childDepth = this.getChild(i).getDepth();
    			if (childDepth > max)
    				max = childDepth;
    		}
    		return max + 1;
    	}
    }
    
    public boolean hasBrothers()
    {
    	if (parent != null)
    	{
    		return parent.getNumChildren() > 1;
    	}
    	return false;
    }
    
    class InOrderIterator implements Iterator<Node<T>> {        
        // constructor 
        List<Integer> parentIndex;
        Node<T> currentNode;
        
    	public InOrderIterator() { 
    		parentIndex = new ArrayList<>();
    		currentNode = Node.this;
    		while (currentNode.getNumChildren() > 0)
    		{
    			parentIndex.add(0);
    			currentNode = currentNode.getChild(0);
    		}
        }
          
        // Checks if the next element exists 
        public boolean hasNext() {
        	return (currentNode == Node.this && parentIndex.size() == 0);
        } 
          
        // moves the cursor/iterator to next element 
        public Node<T> next() {
        	Node<T> node = currentNode;
        	currentNode = node.getParent();
        	/*while (currentNode.getNumChildren() == parentIndex.get(parentIndex.size() - 1))
        	{
        		currentNode = currentNode.getParent();
        		parentIndex.remove(parentIndex.size() - 1);
        	}*/
        	
        	if (currentNode.getNumChildren() > 0)
        	{
        		parentIndex.set(parentIndex.size() - 1, parentIndex.get(parentIndex.size() - 1) + 1);
        		currentNode = currentNode.getChild(parentIndex.get(parentIndex.size() - 1));
        		while (currentNode.getNumChildren() > 0)
        		{
        			parentIndex.add(0);
        			currentNode = currentNode.getChild(0);
        		}
        		
        	}
        	
        	return node;
        	
        } 
          
        // Used to remove an element. Implement only if needed 
        //public void remove() { 
            // Default throws UnsupportedOperationException. 
        //} 
    }  
}
