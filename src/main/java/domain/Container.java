package domain;

public class Container<T> {
	T object;
	String objectType;
	
	public Container(T object,String objectType) {  this.object = object; this.objectType=objectType;  }  // constructor 
	    public T getObject()  { return this.object; } 
	    public String getObjectType()  { return this.objectType; } 

}
