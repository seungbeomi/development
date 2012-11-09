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

import com.onehippo.gogreen.beans.BaseDocument;
import org.hippoecm.hst.jaxrs.model.content.HippoDocumentRepresentation;

/**
 * @version $Id$
 */
@XmlRootElement(name = "basedocument")
public class BaseDocumentRepresentation extends HippoDocumentRepresentation {
    
    public BaseDocumentRepresentation represent(BaseDocument bean) throws RepositoryException {
        super.represent(bean);
        return this;
    }


}
