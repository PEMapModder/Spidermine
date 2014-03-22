package pemapmodder.easymod.xml;

public class Element {
	Element[] subElements={};
	String name;
	Attribute[] attributes;
	public final boolean isNest;
	public Element(String name, Attribute[] attributes, Element[] subElements){
		isNest=true;
	}
	public Element(String name, Attribute[] attributes, String content){
		isNest=false;
	}
}
