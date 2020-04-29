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
    
    public void setValue(T newValue)
    {
    	value = newValue;
    }
    
  

    public void addChild(Node<T> newChild){
    	if (newChild.getParent() != null)
    	{
    		System.out.println("ERROR: Children already has a parent. Could not be added to node.");
    		return;
    	}
    	newChild.setParent(this);
    	children.add(newChild);
    }
    
    //Agrega un hijo en la posicion indicada
    public void addChild(Node<T> newChild, int index){
    	if (newChild.getParent() != null)
    	{
    		System.out.println("ERROR: Children already has a parent. Could not be added to node.");
    		return;
    	}
    	newChild.setParent(this);
    	children.add(index, newChild);
    }
    
    public void removeChild(int i)
    {
    	if (i > 0 && i < children.size())
    		children.remove(i);
    }
    
    public void removeChildren()
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
    	if (i >= 0 && i < children.size())
    	{
    		return children.get(i);
    	}
    	return null;
    }
    
    //Devuelve el indice con la posicion del hijo (comparando punteros)
    //Si no lo encuentra, devuelve -1
    public int getChildPosition(Node<T> toSearch)
    {
    	for (int i = 0; i < children.size(); i++)
    	{
    		if (children.get(i) == toSearch)
    			return i;
    	}
    	return -1;
    }
    
    public Node<T> getParent()
    {
    	return parent;
    }
    
    public void setParent(Node<T> parent)
    {
    	this.parent = parent;
    }
    
    //Obtiene la profundidad del nodo, es decir, cuantos hijos de hijos tiene.
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
    
    //Obtiene la profundidad inversa, es decir, mirando hacia cuantos padres hay hasta la raiz.
    public int getDepthFromRoot()
    {
    	if (this.parent == null)
    		return 1;
    	else
    		return this.parent.getDepthFromRoot() + 1;
    }
    
    public void unlink()
    {
		Node<T> parent = this.getParent();
    	if (parent != null)
    	{
    		int numChild = parent.getNumChildren();
    		for (int i = 0; i < numChild; i++)
    		{
    			if (parent.getChild(i) == this)
    			{
    				parent.removeChild(i);
    				break;
    			}
    		}
    		this.parent = null;
    	}
    }
    
    public void remove()
    {
		this.unlink();
		//TODO: Borrar todos los objetos que cuelguen de este nodo
    }
    
    
    public boolean hasBrothers()
    {
    	if (parent != null)
    	{
    		return parent.getNumChildren() > 1;
    	}
    	return false;
    }
    
    class InOrderIterator implements Iterator<Node<T>> {         // DFS
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
        	return currentNode != null;
        } 
          
        // moves the cursor/iterator to next element 
        public Node<T> next() {
        	Node<T> node = currentNode;
        	currentNode = node.getParent();
        	if (currentNode == null)
        		return node;
        	
        	if (currentNode.getNumChildren() - 1 > parentIndex.get(parentIndex.size() - 1))
        	{
        		int newNum = parentIndex.get(parentIndex.size() - 1) + 1;
        		parentIndex.set(parentIndex.size() - 1, newNum);
        		currentNode = currentNode.getChild(newNum);
        		while (currentNode.getNumChildren() > 0)
        		{
        			parentIndex.add(0);
        			currentNode = currentNode.getChild(0);
        		}
        	}
        	else {
        		parentIndex.remove(parentIndex.size() - 1);
        		return node;
        	}
        	return node;
        } 
          
        // Used to remove an element. Implement only if needed 
        //public void remove() { 
            // Default throws UnsupportedOperationException. 
        //} 
    }
    
    class LevelOrderIterator implements Iterator<Node<T>> {        // Mixto
        // constructor 
        List<Integer> parentIndex;
        Node<T> currentNode;
        
    	public LevelOrderIterator() { 
    		parentIndex = new ArrayList<>();
    		currentNode = Node.this;
    		parentIndex.add(0);
        }
          
        // Checks if the next element exists 
        public boolean hasNext() {
        	return currentNode != Node.this || currentNode.getNumChildren() - 1 >= parentIndex.get(parentIndex.size() - 1);
        } 
          
        // moves the cursor/iterator to next element 
        public Node<T> next() {
        	Node<T> node = currentNode;
        	//currentNode = node.getParent();
        	if (currentNode.getNumChildren() - 1 >= parentIndex.get(parentIndex.size() - 1))
        	{
        		int num = parentIndex.get(parentIndex.size() - 1);
        		currentNode = currentNode.getChild(parentIndex.get(parentIndex.size() - 1));
        		parentIndex.set(parentIndex.size() - 1, num + 1);
        		parentIndex.add(0);
        		
        	}
        	else {
        		while ((currentNode.getNumChildren() - 1 < parentIndex.get(parentIndex.size() - 1)) && (currentNode.getParent() != null))
        		{
        			parentIndex.remove((parentIndex.size() - 1));
        			currentNode = currentNode.getParent();
        		}
        		if (currentNode.getNumChildren() - 1 >= parentIndex.get(parentIndex.size() - 1))
            	{
        			int num = parentIndex.get(parentIndex.size() - 1);
            		currentNode = currentNode.getChild(parentIndex.get(parentIndex.size() - 1));
            		parentIndex.set(parentIndex.size() - 1, num + 1);
            		parentIndex.add(0);
            	}
        	}
        	return node;
        } 
          
        // Used to remove an element. Implement only if needed 
        //public void remove() { 
            // Default throws UnsupportedOperationException. 
        //} 
    }

	public Iterator<Node<T>> iteratorInOrder() {
		return new InOrderIterator();
	}  
	
	public Iterator<Node<T>> iteratorLevelOrder() {
		return new LevelOrderIterator();
	} 

	public String toString()
	{
		return this.value.toString();
	}
}
