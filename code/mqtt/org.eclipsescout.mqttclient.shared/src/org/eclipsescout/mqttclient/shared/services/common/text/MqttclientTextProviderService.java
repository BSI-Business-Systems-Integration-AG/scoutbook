package org.eclipsescout.mqttclient.shared.services.common.text;

import org.eclipse.scout.rt.shared.services.common.text.AbstractDynamicNlsTextProviderService;

public class MqttclientTextProviderService extends AbstractDynamicNlsTextProviderService {
  @Override
  protected String getDynamicNlsBaseName() {
    return "resources.texts.Texts";
  }
}
