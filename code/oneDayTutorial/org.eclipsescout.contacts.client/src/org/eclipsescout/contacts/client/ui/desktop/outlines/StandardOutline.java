package org.eclipsescout.contacts.client.ui.desktop.outlines;

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.extension.client.ui.desktop.outline.AbstractExtensibleOutline;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipsescout.contacts.client.ui.desktop.outlines.pages.CompanyTablePage;
import org.eclipsescout.contacts.client.ui.desktop.outlines.pages.PersonTablePage;

public class StandardOutline extends AbstractExtensibleOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("StandardOutline");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    PersonTablePage personTablePage = new PersonTablePage();
    pageList.add(personTablePage);
    pageList.add(new CompanyTablePage());
  }
}
