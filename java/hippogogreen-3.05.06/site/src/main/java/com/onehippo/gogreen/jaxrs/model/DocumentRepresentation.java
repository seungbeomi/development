/*
 *  Copyright 2010 Hippo.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.onehippo.gogreen.jaxrs.model;

import javax.jcr.RepositoryException;
import javax.xml.bind.annotation.XmlRootElement;

import org.hippoecm.hst.content.rewriter.ContentRewriter;
import org.hippoecm.hst.content.rewriter.impl.SimpleContentRewriter;
import org.hippoecm.hst.core.container.ContainerConstants;
import org.hippoecm.hst.core.request.HstRequestContext;

import com.onehippo.gogreen.beans.Document;

//import com.onehippo.gogreen.beans.compound.Copyright;

/**
 * @version $Id$
 */
@XmlRootElement(name = "document")
public class DocumentRepresentation extends BaseDocumentRepresentation {
    
    private String title;
    private String summary;
    private String description;
    private /*Copyright*/ String copyright;
    
    protected HstRequestContext requestContext;
	protected ContentRewriter contentRewriter;
    
	public DocumentRepresentation(){}
	
	public DocumentRepresentation(HstRequestContext requestContext, ContentRewriter contentRewriter){
		this.requestContext = requestContext;
		this.contentRewriter = contentRewriter == null ? new SimpleContentRewriter() : contentRewriter;
	}
	
    public DocumentRepresentation represent(Document bean) throws RepositoryException {
        super.represent(bean);
        this.title = bean.getTitle();
        this.summary = bean.getSummary();
        
        //This is a quick workaround so as to not create a HippoHtmlRepresentation
        if (bean.getDescription() != null) {
            this.description = (String) contentRewriter.rewrite(
            		bean.getDescription().getContent(),
            		bean.getDescription().getNode(), 
            		requestContext, 
            		ContainerConstants.MOUNT_ALIAS_SITE);
        }
        
        //This is a quick workaround so as to not create a CopyrightRepresentation
        if (bean.getCopyright() != null) {
            this.copyright = bean.getCopyright().getDescription();
        }
        
        return this;
    }
    
  
	public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    
    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public String getCopyright() {
		return copyright;
	}
	

	/* No setter for now
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}*/
}
