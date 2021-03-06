package com.airbus_cyber_security.graylog;

import com.airbus_cyber_security.graylog.alert.AlertRuleService;
import com.airbus_cyber_security.graylog.alert.AlertRuleServiceImpl;
import com.airbus_cyber_security.graylog.alert.rest.AlertRuleResource;
import com.airbus_cyber_security.graylog.audit.AlertWizardAuditEventTypes;
import com.airbus_cyber_security.graylog.config.rest.AlertWizardConfigResource;
import com.airbus_cyber_security.graylog.list.AlertListService;
import com.airbus_cyber_security.graylog.list.AlertListServiceImpl;
import com.airbus_cyber_security.graylog.list.rest.AlertListResource;
import com.airbus_cyber_security.graylog.permissions.AlertRuleRestPermissions;
import org.graylog2.plugin.PluginConfigBean;
import org.graylog2.plugin.PluginModule;

import java.util.Collections;
import java.util.Set;

/**
 * Extend the PluginModule abstract class here to add you plugin to the system.
 */
public class AlertWizardModule extends PluginModule {
    /**
     * Returns all configuration beans required by this plugin.
     *
     * Implementing this method is optional. The default method returns an empty {@link Set}.
     */
    @Override
    public Set<? extends PluginConfigBean> getConfigBeans() {
        return Collections.emptySet();
    }

    @Override
    protected void configure() {
    	bind(AlertRuleService.class).to(AlertRuleServiceImpl.class);
        bind(AlertListService.class).to(AlertListServiceImpl.class);

        addPermissions(AlertRuleRestPermissions.class);
        addRestResource(AlertRuleResource.class);
        addAuditEventTypes(AlertWizardAuditEventTypes.class);
        addRestResource(AlertWizardConfigResource.class);
        addRestResource(AlertListResource.class);
    }
}
