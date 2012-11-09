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

package com.onehippo.gogreen.components.events;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.query.filter.Filter;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onehippo.gogreen.beans.EventDocument;
import com.onehippo.gogreen.beans.compound.Address;
import com.onehippo.gogreen.components.BaseComponent;

public class Layar extends BaseComponent {

    private static final Logger log = LoggerFactory.getLogger(Layar.class);
    private static final int DEFAULT_DISTANCE = 5000;
    private static final int MULTIPLY_BY = 1000000;

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        HippoBean currentBean = this.getContentBean(request);
        if (currentBean == null) {
            return;
        }

        try {
            HstQuery hstQuery = this.getQueryManager(request).createQuery(getSiteContentBaseBean(request), EventDocument.class);

            Filter f = hstQuery.createFilter();
            Filter f1 = hstQuery.createFilter();
            Filter f2 = hstQuery.createFilter();
            f1.addNotEqualTo("hippogogreen:location/hippogogreen:latitude", 0.0);
            f2.addNotEqualTo("hippogogreen:location/hippogogreen:longitude", 0.0);
            f.addAndFilter(f1);
            f.addAndFilter(f2);
            hstQuery.setFilter(f);

            HstQueryResult result = hstQuery.execute();
            HippoBeanIterator it = result.getHippoBeans();

            JSONObject obj = new JSONObject();
            JSONArray arr = new JSONArray();
            JSONArray emptyAction = new JSONArray();

            while (it.hasNext()) {
                EventDocument next = (EventDocument) it.next();
                Address location = next.getLocation();

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", next.toString());
                map.put("attribution", "Hippo B.V.");
                map.put("title", next.getTitle());
                map.put("lat", location.getLatitude()*MULTIPLY_BY);
                map.put("lon", location.getLongitude()*MULTIPLY_BY);
                map.put("actions", emptyAction);
                map.put("imageURL", getExternalImagePath(next, request));
                map.put("line2", new SimpleDateFormat("dd-MMM-yyyy HH:mm").format(next.getDate().getTime()));
                map.put("line3", location.getStreet() + " " + location.getNumber());
                map.put("line4", "distance:%distance%");
                map.put("type", 0);
                map.put("distance", DEFAULT_DISTANCE);

                arr.put(map);
            }

            obj.put("hotspots", arr);
            obj.put("layer", "gogreenevents");
            obj.put("errorString", "ok");
            obj.put("errorCode", "0");
            String jsonString = obj.toString();

            request.setAttribute("json", jsonString);

        } catch (QueryException qe) {
            log.error("Error while getting the documents " + qe.getMessage(), qe);
        } catch (JSONException e) {
            log.error("Something wrong with usage of json " + e.getMessage(), e);
        }

    }

    private String getExternalImagePath(EventDocument bean, HstRequest request) {
        if (bean.getFirstImage() != null && bean.getFirstImage().getThumbnail() != null) {
            HstRequestContext reqContext = request.getRequestContext();
            return reqContext.getHstLinkCreator().create(bean.getFirstImage().getThumbnail().getNode(), reqContext).toUrlForm(reqContext, true);
        }
        return "";
    }

}
