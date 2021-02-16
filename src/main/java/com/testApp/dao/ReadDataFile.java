package com.testApp.dao;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import javax.xml.stream.XMLStreamException;
import javax.rmi.CORBA.*;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.OMText;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.*;

import javax.transaction.*;

/**
 * 
 * @author Wipro
 */
@SuppressWarnings("unused")
public class ReadDataFile {
	
	
	protected  OMElement addOneDocument(OMElement request, String document, String documentId, boolean includeWhitespace) throws IOException {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace ns = fac.createOMNamespace("wipro:app:IT:spring-b:2019" , null);
		OMElement docElem = fac.createOMElement("Document", ns);
		docElem.addAttribute("id", documentId, null);

        // A string, turn it into an StreamSource
	    DataSource ds = new ByteArrayDataSource(document, "text/xml"); 
		DataHandler handler = new DataHandler(ds);
		 
        OMText binaryData = fac.createOMText(handler, true);
        if (includeWhitespace)
        {
	        /** The whitespace */
	        docElem.addChild(fac.createOMText("\n"));
        }
        docElem.addChild(binaryData);

        Iterator iter = request.getChildrenWithLocalName("SubmitObjectsRequest");
        OMElement submitObjectsRequest = null;
        for (;iter.hasNext();) {
        	submitObjectsRequest = (OMElement)iter.next();
        	if (submitObjectsRequest != null)
        		break;
        }
        submitObjectsRequest.insertSiblingAfter(docElem);
        return request;
	}
}