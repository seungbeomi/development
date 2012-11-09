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

package com.onehippo.gogreen.components;

import javax.jcr.RepositoryException;

import org.hippoecm.hst.core.component.HstComponentException;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.onehippo.forge.poll.component.PollProvider;

/**
 * User: vivek
 * Date: Jul 12, 2010
 * Time: 11:49:45 AM
 */
public class PollComponent extends BaseComponent {
    private final PollProvider pollProvider = new PollProvider(this);

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        pollProvider.doBeforeRender(request, response);
    }

    @Override
    public void doAction(HstRequest request, HstResponse response) {

        try {
            pollProvider.doAction(request, response, getPersistableSession(request));
        }
        catch (RepositoryException e) {
            throw new HstComponentException(e);
        }
    }
}
