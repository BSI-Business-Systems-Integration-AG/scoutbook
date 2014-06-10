package org.eclipse.scout.transport.ui.rap;

import org.eclipse.scout.rt.ui.rap.mobile.AbstractTabletStandaloneRwtEnvironment;
import org.eclipse.scout.transport.client.ClientSession;

public class TabletStandaloneRwtEnvironment extends AbstractTabletStandaloneRwtEnvironment {

  public TabletStandaloneRwtEnvironment() {
    super(Activator.getDefault().getBundle(), ClientSession.class);
  }
}
