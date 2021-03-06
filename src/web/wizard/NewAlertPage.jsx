import PropTypes from 'prop-types';
import React from 'react';
import createReactClass from 'create-react-class';
import {Button, Col, Row} from 'react-bootstrap';
import {LinkContainer} from 'react-router-bootstrap';
import {DocumentTitle, PageHeader, Spinner} from 'components/common';
import CreateAlertInput from './CreateAlertInput';
import Routes from 'routing/Routes';
import {addLocaleData, IntlProvider, FormattedMessage} from 'react-intl';
import messages_fr from '../translations/fr.json';
import WizardConfigurationsActions from "../config/WizardConfigurationsActions";

let frLocaleData = require('react-intl/locale-data/fr');
const language = navigator.language.split(/[-_]/)[0];
addLocaleData(frLocaleData);

const messages = {
            'fr': messages_fr
        };

const NewAlertPage = createReactClass({
    displayName: 'NewAlertPage',

    getInitialState() {
        return {
            configurations: null,
        };
    },
    propTypes: {
        location: PropTypes.object.isRequired,
        params: PropTypes.object.isRequired,
        children: PropTypes.element,
    },

    componentDidMount() {
        WizardConfigurationsActions.list().then(configurations => {
            this.setState({configurations: configurations});
        });
    },

    _isConfigurationLoading() {
        return !this.state.configurations;
    },

    render() {

        let componentCreateAlertInput;
        if (this._isConfigurationLoading()) {
            componentCreateAlertInput = <Spinner/>;
        }else{
            componentCreateAlertInput = <CreateAlertInput create={true} default_values={this.state.configurations.default_values}/>;
        }

        return (
          <IntlProvider locale={language} messages={messages[language]}>
            <DocumentTitle title="New alert rule">
                <div>
                    <PageHeader title={<FormattedMessage id= "wizard.newAlertRule" defaultMessage= "Wizard: New alert rule" />}>
                        <span>
                            <FormattedMessage id= "wizard.define" defaultMessage= "You can define an alert rule." />
                        </span>
                        <span>
                            <FormattedMessage id="wizard.documentation" 
                            defaultMessage= "Read more about Wizard alert rules in the documentation." />
                        </span>
                        <span>
                            <LinkContainer to={Routes.pluginRoute('WIZARD_ALERTRULES')}>
                                <Button bsStyle="info"><FormattedMessage id= "wizard.back" defaultMessage= "Back to alert rules" /></Button>
                            </LinkContainer>
                            &nbsp;
                        </span>
                    </PageHeader>
                    <Row className="content">
                        <Col md={12}>
                            {componentCreateAlertInput}
                        </Col>
                    </Row>
                </div>
            </DocumentTitle>
          </IntlProvider>
        );
    },
});

export default NewAlertPage;
