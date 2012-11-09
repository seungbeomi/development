package com.onehippo.gogreen.components.common;

import org.hippoecm.hst.core.parameters.DocumentLink;
import org.hippoecm.hst.core.parameters.Parameter;

/**
 * HST Component Parameters Info class for SimpleDocument. Used by PageComposer.
 */
public interface SimpleDocumentParamsInfo {
    String PARAM_DOCUMENTLOCATION = "documentlocation";

    @Parameter(name = PARAM_DOCUMENTLOCATION, required = true, displayName = "Document")
    @DocumentLink(docType = "hippogogreen:simpledocument", allowCreation = true, docLocation = "common/simpledocuments")
    String getDocumentLocation();
}
