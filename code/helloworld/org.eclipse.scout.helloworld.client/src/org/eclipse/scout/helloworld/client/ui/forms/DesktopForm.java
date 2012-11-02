package org.eclipse.scout.helloworld.client.ui.forms;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.helloworld.client.ui.forms.DesktopForm.MainBox.DesktopBox;
import org.eclipse.scout.helloworld.client.ui.forms.DesktopForm.MainBox.DesktopBox.MessageField;
import org.eclipse.scout.helloworld.shared.Icons;
import org.eclipse.scout.helloworld.shared.services.process.DesktopFormData;
import org.eclipse.scout.helloworld.shared.services.process.IDesktopProcessService;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

@FormData(value = DesktopFormData.class, sdkCommand = SdkCommand.CREATE)
public class DesktopForm extends AbstractForm {

  public DesktopForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected int getConfiguredDisplayHint() {
    return DISPLAY_HINT_VIEW;
  }

  @Override
  protected String getConfiguredDisplayViewId() {
    return VIEW_ID_CENTER;
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.EclipseScout;
  }

  public DesktopBox getDesktopBox() {
    return getFieldByClass(DesktopBox.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public MessageField getMessageField() {
    return getFieldByClass(MessageField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class DesktopBox extends AbstractGroupBox {

      @Order(10.0)
      public class MessageField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Message");
        }
      }
    }
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      IDesktopProcessService service = SERVICES.getService(IDesktopProcessService.class);
      DesktopFormData formData = new DesktopFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);

    }
  }

  public void startView() throws ProcessingException {
    startInternal(new ViewHandler());
  }
}
