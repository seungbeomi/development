/**
 * Copyright (C) 2010 Hippo B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.onehippo.gogreen.beans;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

/*

[hippogogreen:restapi] > hippogogreen:basedocument
- hippogogreen:type (string)
- hippogogreen:summary (string)
+ hippogogreen:documentation (hippostd:html)
- hippogogreen:url (string)
- hippogogreen:response (string)
- hippogogreen:path (string)

*/
@Node(jcrType = "hippogogreen:restapi")
public class RestApi extends BaseDocument {

    private String path;

    private String type;
    private String summary;
    private HippoHtml documentation;
    private String url;
    private String response;


    public String getApiPath() {
        return (path == null) ? (String) getProperty("hippogogreen:path") : path;
    }

    public String getType() {
        return (type == null) ? (String) getProperty("hippogogreen:type") : type;
    }

    public String getSummary() {
        return (summary == null) ? (String) getProperty("hippogogreen:summary") : summary;
    }

    public HippoHtml getDocumentation() {
        if (documentation ==null){
            documentation  =  getBean("hippogogreen:documentation");
        }
        return documentation ;
    }

    public String getUrl() {
        return (url == null) ? (String) getProperty("hippogogreen:url") : url;
    }

    public String getResponse() {
        return (response == null) ? (String) getProperty("hippogogreen:response") : response;
    }

}
