package com.onehippo.gogreen.selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.hippoecm.frontend.model.IModelReference;
import org.hippoecm.frontend.model.JcrNodeModel;
import org.hippoecm.frontend.plugin.IPluginContext;
import org.hippoecm.frontend.plugin.Plugin;
import org.hippoecm.frontend.plugin.config.IPluginConfig;
import org.hippoecm.repository.api.HippoNodeType;
import org.onehippo.forge.selection.frontend.model.ValueList;
import org.onehippo.forge.selection.frontend.provider.IValueListProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalizingValueListProvider extends Plugin implements IValueListProvider {
    private static final long serialVersionUID = 1L;

    static final Logger log = LoggerFactory.getLogger(LocalizingValueListProvider.class);

    public LocalizingValueListProvider(IPluginContext context, IPluginConfig config) {
        super(context, config);

        context.registerService(this, config.getString(IValueListProvider.SERVICE));
    }

    private String getLocale() {
        IModelReference modelRef = getPluginContext().getService(getPluginConfig().getString("wicket.model"),
                IModelReference.class);
        if (modelRef != null && modelRef.getModel() != null) {
            JcrNodeModel model = (JcrNodeModel) modelRef.getModel();
            Node node = model.getObject();
            if (node != null) {
                try {
                    while (node.getDepth() > 0 && !node.isNodeType(HippoNodeType.NT_DOCUMENT)) {
                        node = node.getParent();
                    }
                    if (node.isNodeType(HippoNodeType.NT_DOCUMENT) && node.isNodeType("hippotranslation:translated")) {
                        return node.getProperty("hippotranslation:locale").getString();
                    }
                } catch (RepositoryException ex) {
                    log.error("Could not determine locale of containing document", ex);
                }
            }
        }
        return null;
    }

    private String getSource(String name) {
        String locale = getLocale();
        if (locale != null) {
            IPluginConfig config = getPluginConfig().getPluginConfig("locale." + locale);
            if (config != null) {
                return config.getString(name, name);
            }
        }
        return null;
    }

    public ValueList getValueList(IPluginConfig config) {
        throw new UnsupportedOperationException();
    }

    public ValueList getValueList(String name) {
        String sourceName = getSource(name);
        if (sourceName != null) {
            IValueListProvider provider = getPluginContext().getService(
                    getPluginConfig()
                            .getString("valuelist.provider.original", IValueListProvider.SERVICE_VALUELIST_DEFAULT),
                    IValueListProvider.class);
            if (provider != null) {
                return provider.getValueList(sourceName);
            }
        }
        return new ValueList();
    }
    
    @Override
    public ValueList getValueList(String name, Locale locale) {
        return getValueList(name);
    }

    public List<String> getValueListNames() {
        String locale = getLocale();
        if (locale != null) {
            IPluginConfig config = getPluginConfig().getPluginConfig("locale." + locale);
            if (config != null) {
                return new ArrayList<String>(config.keySet());
            }
        }
        return Collections.emptyList();
    }

}
