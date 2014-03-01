package quantumworks.easymod.xml;

public class XmlLangException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public XmlLangException(String msg){
		this(msg, -1);
	}
	public XmlLangException(){
		this("");
	}
	public XmlLangException(int line){
		this("", line);
	}
	public XmlLangException(String msg, int line){
		super(Integer.toString(line)+':'+msg);
	}
}
