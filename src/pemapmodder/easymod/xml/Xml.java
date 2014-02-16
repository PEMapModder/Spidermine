package pemapmodder.easymod.xml;

import java.io.File;
import java.io.IOException;

import pemapmodder.spidermine.utils.io.MyReader;

public class Xml {
	private final static int STATUS_RAW_CONTENT=0x0;
	private final static int STATUS_ELEMENT_NAME=0x1;
	private final static int STATUS_ATTRIBUTE_NAME=0x2;
	private final static int STATUS_ATTRIBUTE_VAL_ONE=0x3;
	private final static int STATUS_ATTRIBUTE_VAL_SGL_QUOTE=0x4;
	private final static int STATUS_ATTRIBUTE_VAL_DBL_QUOTE=0x5;
	private final static int STATUS_COMMENT=0x6;
	Element[] elements={};
	@SuppressWarnings("unused")
	private Xml(String src) throws XmlLangException{
		int nestTags=0;
		String[] nests={};
		int memory=-1;
		int status=STATUS_RAW_CONTENT;
		String[] buffer={
				"","","","","","","","","",""
		};
		int line=0;
		for(int i=0; i<src.length(); i++){
			char tc=src.charAt(i);
			if(i==0&&tc!='<')
				throw new XmlLangException(line);
			if(tc=='\n'){
				line++;
				continue;
			}
			switch(status){
			case STATUS_RAW_CONTENT:
				if(tc=='<'){
					status=STATUS_ELEMENT_NAME;
					continue;
				}
				//TODO
				break;
			case STATUS_ELEMENT_NAME:
				if(tc==' '){
					nestTags++;
					
					status=STATUS_ATTRIBUTE_NAME;
					continue;
				}
				buffer[1]+=tc;
				break;
			case STATUS_ATTRIBUTE_NAME:
				
				break;
			case STATUS_ATTRIBUTE_VAL_ONE:
				break;
			case STATUS_ATTRIBUTE_VAL_SGL_QUOTE:
			case STATUS_ATTRIBUTE_VAL_DBL_QUOTE:
				break;
			case STATUS_COMMENT:
				break;
			}
		}
	}
	
	public static Xml parse(File f)
			throws IOException, XmlLangException{
		//TODO parse
		String src=MyReader.readAll(f);
		return new Xml(src);
	}

}
