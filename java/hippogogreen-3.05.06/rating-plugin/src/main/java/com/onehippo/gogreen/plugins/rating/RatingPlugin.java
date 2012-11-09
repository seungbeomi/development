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

package com.onehippo.gogreen.plugins.rating;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import org.apache.wicket.markup.html.basic.Label;
import org.hippoecm.frontend.model.JcrNodeModel;
import org.hippoecm.frontend.plugin.IPluginContext;
import org.hippoecm.frontend.plugin.config.IPluginConfig;
import org.hippoecm.frontend.service.IEditor;
import org.hippoecm.frontend.service.render.RenderPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Rating Plugin to display readonly fields for Votes and Average Rating
 *
 * @author Vijay Kiran
 */
public class RatingPlugin extends RenderPlugin {

    private static final Logger LOGGER = LoggerFactory.getLogger(RatingPlugin.class);
    private static final long serialVersionUID = 1L;

    public RatingPlugin(IPluginContext context, IPluginConfig config) {
        super(context, config);

        final JcrNodeModel nodeModel = (JcrNodeModel) getModel();
        Node productNode = nodeModel.getNode();

        double avgRating = 0;
        long reviewCount = 0;

        try {
            Node parent = productNode.getParent();
            if (parent.isNodeType("mix:referenceable")) {
                String query = "//*[(*/@hippo:docbase = '" + parent.getUUID() + "') and (@jcr:primaryType='hippogogreen:review')]";

                QueryManager queryManager = productNode.getSession().getWorkspace().getQueryManager();
                Query reviewsQuery = queryManager.createQuery(query, Query.XPATH);

                QueryResult queryResult = reviewsQuery.execute();

                long totalRating = 0L;
                final NodeIterator iterator = queryResult.getNodes();
                while (iterator.hasNext()) {
                    reviewCount++;
                    final Node review = iterator.nextNode();
                    if (review.hasProperty("hippogogreen:rating")) {
                        totalRating += review.getProperty("hippogogreen:rating").getLong();
                    }
                }

                if (reviewCount != 0) {
                    avgRating = (double) totalRating / (double) reviewCount;
                }

                IEditor.Mode mode = IEditor.Mode.fromString(config.getString("mode", "view"));
                if (mode == IEditor.Mode.EDIT) {
                    productNode.setProperty("hippogogreen:rating", avgRating);
                    productNode.setProperty("hippogogreen:votes", reviewCount);
                }
            }
        } catch (RepositoryException e) {
            LOGGER.error("Error occurred whilie initializing rating plugin: " + e.getMessage(), e);
        }

        add(new Label("avgRating", String.valueOf(avgRating)));
        add(new Label("reviewCount", String.valueOf(reviewCount)));
    }
}
