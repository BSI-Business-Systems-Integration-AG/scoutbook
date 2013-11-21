/*******************************************************************************
 * Copyright (c) 2013 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipse.scout.widget.client.ui.forms;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParsePosition;

import org.eclipse.scout.commons.NumberUtility;
import org.eclipse.scout.commons.StringUtility;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
import org.eclipse.scout.rt.client.ui.form.fields.bigdecimalfield.AbstractBigDecimalField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.checkbox.AbstractCheckBox;
import org.eclipse.scout.rt.client.ui.form.fields.doublefield.AbstractDoubleField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.integerfield.AbstractIntegerField;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.placeholder.AbstractPlaceholderField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.ScoutTexts;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.CloseButton;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ConfigurationBox;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ConfigurationBox.BigDecimalInputField;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ConfigurationBox.FractionDigitsField;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ConfigurationBox.GetValue0Field;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ConfigurationBox.GroupingField;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ConfigurationBox.InputField;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ConfigurationBox.MaxFractionDigitsField;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ConfigurationBox.MaximumValueField;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ConfigurationBox.MinFractionDigitsField;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ConfigurationBox.MinimumValueField;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ConfigurationBox.MultiplierField;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ConfigurationBox.PercentField;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ConfigurationBox.Place1Field;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ConfigurationBox.Place2Field;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ConfigurationBox.Place3Field;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ConfigurationBox.Place4Field;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ConfigurationBox.Place5Field;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ExamplesBox;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ExamplesBox.BigDecimalDisabledField;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ExamplesBox.BigDecimalField;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ExamplesBox.BigDecimalMandatoryField;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ExamplesBox.BigDecimalStyledField;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ExamplesBox.DisabledField;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ExamplesBox.DoubleColumnField;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ExamplesBox.DoubleField;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ExamplesBox.MandatoryField;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.ExamplesBox.StyledField;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.HighestValueButton;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.PiButton;
import org.eclipse.scout.widget.client.ui.forms.DecimalFieldsForm.MainBox.SmallestValueButton;
import org.eclipse.scout.widget.client.ui.forms.NumberFieldsForm.MainBox.ExamplesBox.BigIntegerColumnField;
import org.eclipse.scout.widget.client.ui.forms.StringFieldForm.MainBox.ConfigurationBox.PlaceholderField;

public class DecimalFieldsForm extends AbstractForm implements IPageForm {

  private static final double MIN_VALUE = -Math.PI;
  private static final double MAX_VALUE = Math.PI;

  private static final BigDecimal TWO = new BigDecimal(2);
  private static final BigDecimal FOUR = new BigDecimal(4);

  public DecimalFieldsForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("DecimalFields");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public BigDecimalField getBigDecimalField() {
    return getFieldByClass(BigDecimalField.class);
  }

  @Override
  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public DisabledField getDisabledField() {
    return getFieldByClass(DisabledField.class);
  }

  public DoubleField getDoubleField() {
    return getFieldByClass(DoubleField.class);
  }

  public FractionDigitsField getFractionDigitsField() {
    return getFieldByClass(FractionDigitsField.class);
  }

  public GetValue0Field getGetValue0Field() {
    return getFieldByClass(GetValue0Field.class);
  }

  public GroupingField getGroupingField() {
    return getFieldByClass(GroupingField.class);
  }

  public HighestValueButton getHighestValueButton() {
    return getFieldByClass(HighestValueButton.class);
  }

  public InputField getInputField() {
    return getFieldByClass(InputField.class);
  }

  public DoubleColumnField getIntegerColumnField() {
    return getFieldByClass(DoubleColumnField.class);
  }

  public DoubleField getIntegerField() {
    return getFieldByClass(DoubleField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public BigDecimalDisabledField getBigIntDisabledField() {
    return getFieldByClass(BigDecimalDisabledField.class);
  }

  public BigDecimalMandatoryField getBigIntMandatoryField() {
    return getFieldByClass(BigDecimalMandatoryField.class);
  }

  public BigDecimalStyledField getBigIntStyledField() {
    return getFieldByClass(BigDecimalStyledField.class);
  }

  public BigIntegerColumnField getBigIntegerColumnField() {
    return getFieldByClass(BigIntegerColumnField.class);
  }

  public BigDecimalInputField getBigDecimalInputField() {
    return getFieldByClass(BigDecimalInputField.class);
  }

  public MandatoryField getMandatoryField() {
    return getFieldByClass(MandatoryField.class);
  }

  public MaxFractionDigitsField getMaxFractionDigitsField() {
    return getFieldByClass(MaxFractionDigitsField.class);
  }

  public MaximumValueField getMaximumValueField() {
    return getFieldByClass(MaximumValueField.class);
  }

  public MinFractionDigitsField getMinFractionDigitsField() {
    return getFieldByClass(MinFractionDigitsField.class);
  }

  public MinimumValueField getMinimumValueField() {
    return getFieldByClass(MinimumValueField.class);
  }

  public ExamplesBox getExamplesBox() {
    return getFieldByClass(ExamplesBox.class);
  }

  public Place1Field getPlace1Field() {
    return getFieldByClass(Place1Field.class);
  }

  public Place2Field getPlace2Field() {
    return getFieldByClass(Place2Field.class);
  }

  public Place3Field getPlace3Field() {
    return getFieldByClass(Place3Field.class);
  }

  public MultiplierField getMultiplierField() {
    return getFieldByClass(MultiplierField.class);
  }

  public PercentField getPercentField() {
    return getFieldByClass(PercentField.class);
  }

  public PiButton getPiButton() {
    return getFieldByClass(PiButton.class);
  }

  public Place4Field getPlace4Field() {
    return getFieldByClass(Place4Field.class);
  }

  public Place5Field getPlace5Field() {
    return getFieldByClass(Place5Field.class);
  }

  public PlaceholderField getPlaceholderField() {
    return getFieldByClass(PlaceholderField.class);
  }

  public SmallestValueButton getSmallestValueButton() {
    return getFieldByClass(SmallestValueButton.class);
  }

  public ConfigurationBox getSpecialCasesBox() {
    return getFieldByClass(ConfigurationBox.class);
  }

  public StyledField getStyledField() {
    return getFieldByClass(StyledField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class ExamplesBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Examples");
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }

      @Order(10.0)
      public class DoubleColumnField extends AbstractLabelField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DoubleField");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "BOLD";
        }
      }

      @Order(20.0)
      public class DoubleField extends AbstractDoubleField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Default");
        }
      }

      @Order(30.0)
      public class MandatoryField extends AbstractDoubleField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Mandatory");
        }

        @Override
        protected boolean getConfiguredMandatory() {
          return true;
        }
      }

      @Order(40.0)
      public class DisabledField extends AbstractDoubleField {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Disabled");
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(Math.E);
        }
      }

      @Order(50.0)
      public class StyledField extends AbstractDoubleField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Styled");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          if (getValue() < 0) {
            setForegroundColor("FF0000");
          }
          else {
            setForegroundColor(null);
          }
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(-3.1415);
          setForegroundColor("FF0000");
        }
      }

      @Order(110.0)
      public class BigDecimalColumnField extends AbstractLabelField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("BigDecimalField");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "BOLD";
        }
      }

      @Order(120.0)
      public class BigDecimalField extends AbstractBigDecimalField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Default");
        }
      }

      @Order(130.0)
      public class BigDecimalMandatoryField extends AbstractBigDecimalField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Mandatory");
        }

        @Override
        protected boolean getConfiguredMandatory() {
          return true;
        }
      }

      @Order(140.0)
      public class BigDecimalDisabledField extends AbstractBigDecimalField {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Disabled");
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(BigDecimal.valueOf(Math.E));
        }
      }

      @Order(150.0)
      public class BigDecimalStyledField extends AbstractBigDecimalField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Styled");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          if (getValue().signum() < 0) {
            setForegroundColor("FF0000");
          }
          else {
            setForegroundColor(null);
          }
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(BigDecimal.valueOf(-3.5));
          setForegroundColor("FF0000");
        }
      }
    }

    @Order(20.0)
    public class ConfigurationBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Configure");
      }

      @Order(10.0)
      public class InputField extends AbstractDoubleField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DoubleFieldInput");
        }

        public void setGrouping(boolean grouping) {
          setGroupingUsed(grouping);
        }
      }

      @Order(20.0)
      public class GetValue0Field extends AbstractStringField {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("GetValue");
        }

        @Override
        protected Class<? extends IValueField> getConfiguredMasterField() {
          return InputField.class;
        }

        @Override
        protected void execChangedMasterValue(Object newMasterValue) throws ProcessingException {
          if (newMasterValue != null) {
            setValue(((Double) newMasterValue).toString());
          }
          else {
            setValue(null);
          }
        }
      }

      @Order(30.0)
      public class MinimumValueField extends AbstractDoubleField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("MinimumValue");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          if (getValue() != null) {
            getInputField().setMinValue(getValue());
            getBigDecimalInputField().setMinValue(BigDecimal.valueOf(getValue()));
          }
          else {
            getInputField().setMinValue(null);
            getBigDecimalInputField().setMinValue(null);
          }

          getInputField().validateContent();
          getBigDecimalInputField().validateContent();
        }
      }

      @Order(40.0)
      public class MaximumValueField extends AbstractDoubleField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("MaximumValue");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          if (getValue() != null) {
            getInputField().setMaxValue(getValue());
            getBigDecimalInputField().setMaxValue(BigDecimal.valueOf(getValue()));
          }
          else {
            getInputField().setMaxValue(null);
            getBigDecimalInputField().setMaxValue(null);
          }

          getInputField().validateContent();
          getBigDecimalInputField().validateContent();
        }
      }

      @Order(50.0)
      public class GroupingField extends AbstractCheckBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Grouping");
        }

        @Override
        protected String getConfiguredFont() {
          return "ITALIC";
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(getInputField().isGroupingUsed());
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getInputField().setGrouping(getValue());
          getBigDecimalInputField().setGrouping(getValue());
        }
      }

      @Order(60.0)
      public class MinFractionDigitsField extends AbstractIntegerField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("MinFractionDigits");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(getInputField().getMinFractionDigits());
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getInputField().setMinFractionDigits(NumberUtility.nvl(getValue(), 0));
          getBigDecimalInputField().setMinFractionDigits(NumberUtility.nvl(getValue(), 0));
        }
      }

      @Order(70.0)
      public class MaxFractionDigitsField extends AbstractIntegerField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("MaxFractionDigits");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(getInputField().getMaxFractionDigits());
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getInputField().setMaxFractionDigits(NumberUtility.nvl(getValue(), 2));
          getBigDecimalInputField().setMaxFractionDigits(NumberUtility.nvl(getValue(), 2));
        }
      }

      @Order(80.0)
      public class FractionDigitsField extends AbstractIntegerField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("FractionDigits");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(getInputField().getFractionDigits());
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getInputField().setFractionDigits(NumberUtility.nvl(getValue(), 2));
          getBigDecimalInputField().setFractionDigits(NumberUtility.nvl(getValue(), 2));
        }
      }

      @Order(90.0)
      public class MultiplierField extends AbstractIntegerField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Multiplier");
        }

        @Override
        protected String getConfiguredLabelFont() {
          return "ITALIC";
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(getInputField().getMultiplier());
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getInputField().setMultiplier(NumberUtility.nvl(getValue(), 1));
          getBigDecimalInputField().setMultiplier(NumberUtility.nvl(getValue(), 1));
        }
      }

      @Order(100.0)
      public class PercentField extends AbstractCheckBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Percent");
        }

        @Override
        protected String getConfiguredFont() {
          return "ITALIC";
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getInputField().setPercent(getValue());
          getBigDecimalInputField().setPercent(getValue());
        }
      }

      @Order(110.0)
      public class BigDecimalInputField extends AbstractBigDecimalField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("BigDecimalFieldInput");
        }

        public void setGrouping(boolean grouping) {
          setGroupingUsed(grouping);
        }

        /**
         * Parses the provided text and returns its BigInteger representation.
         * If the provided text does not syntactically represent an integer number,
         * an exception is thrown.
         */
        @Override
        protected BigDecimal execParseValue(String text) throws ProcessingException {
          BigDecimal retVal = null;
          text = StringUtility.nvl(text, "").trim();
          if (text.length() > 0) {
            DecimalFormat df = (DecimalFormat) createNumberFormat();
            df.setParseBigDecimal(true);
            ParsePosition p = new ParsePosition(0);

            if (isPercent()) {
              if (text.endsWith("%")) {
                text = StringUtility.trim(text.substring(0, text.length() - 1));
              }
              text = StringUtility.concatenateTokens(text, df.getPositiveSuffix());
            }

            BigDecimal val = (BigDecimal) df.parse(text, p);
            // check for bad syntax
            if (p.getErrorIndex() >= 0 || p.getIndex() != text.length()) {
              throw new ProcessingException(ScoutTexts.get("InvalidNumberMessageX", text));
            }
            retVal = val;
          }
          return retVal;
        }
      }

      @Order(120.0)
      public class GetValue1Field extends AbstractStringField {

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("GetValue");
        }

        @Override
        protected Class<? extends IValueField> getConfiguredMasterField() {
          return BigDecimalInputField.class;
        }

        @Override
        protected void execChangedMasterValue(Object newMasterValue) throws ProcessingException {
          if (newMasterValue != null) {
            setValue(((BigDecimal) newMasterValue).toString());
          }
          else {
            setValue(null);
          }
        }
      }

      @Order(122.0)
      public class Place1Field extends AbstractPlaceholderField {
      }

      @Order(123.0)
      public class Place2Field extends AbstractPlaceholderField {
      }

      @Order(124.0)
      public class Place3Field extends AbstractPlaceholderField {
      }

      @Order(125.0)
      public class Place4Field extends AbstractPlaceholderField {
      }

      @Order(130.0)
      public class Place5Field extends AbstractPlaceholderField {
      }

      @Order(140.0)
      public class Place6Field extends AbstractPlaceholderField {
      }

      @Order(150.0)
      public class Place7Field extends AbstractPlaceholderField {
      }
    }

    @Order(30.0)
    public class HighestValueButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("HighestValue");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        getInputField().setValue(Double.MAX_VALUE);
        getBigDecimalInputField().setDisplayText("can get as large as you want");
      }
    }

    @Order(40.0)
    public class SmallestValueButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("SmallestValue");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        getInputField().setValue(Double.MIN_VALUE);
        getBigDecimalInputField().setDisplayText("can get as small as you want");
      }
    }

    @Order(50.0)
    public class PiButton extends AbstractButton {

      /**
       * Gauss-Legendre Algorithm
       * implementation copied from http://java.lykkenborg.no/2005/03/computing-pi-using-bigdecimal.html
       */
      private BigDecimal pi() {
        int scale = NumberUtility.nvl(getFractionDigitsField().getValue(), 20);
        BigDecimal a = BigDecimal.ONE;
        BigDecimal b = BigDecimal.ONE.divide(sqrt(TWO, scale), scale, BigDecimal.ROUND_HALF_UP);
        BigDecimal t = new BigDecimal(0.25);
        BigDecimal x = BigDecimal.ONE;
        BigDecimal y;

        while (!a.equals(b)) {
          y = a;
          a = a.add(b).divide(TWO, scale, BigDecimal.ROUND_HALF_UP);
          b = sqrt(b.multiply(y), scale);
          t = t.subtract(x.multiply(y.subtract(a).multiply(y.subtract(a))));
          x = x.multiply(TWO);
        }

        return a.add(b).multiply(a.add(b)).divide(t.multiply(FOUR), scale, BigDecimal.ROUND_HALF_UP);
      }

      /**
       * Babylonian square root method (Newton's method)
       */
      private BigDecimal sqrt(BigDecimal a, int scale) {
        BigDecimal x0 = new BigDecimal("0");
        BigDecimal x1 = new BigDecimal(Math.sqrt(a.doubleValue()));

        while (!x0.equals(x1)) {
          x0 = x1;
          x1 = a.divide(x0, scale, BigDecimal.ROUND_HALF_UP);
          x1 = x1.add(x0);
          x1 = x1.divide(TWO, scale, BigDecimal.ROUND_HALF_UP);
        }

        return x1;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Pi");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        getInputField().setValue(Math.PI);
        getBigDecimalInputField().setValue(pi());
      }
    }

    @Order(60.0)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}
