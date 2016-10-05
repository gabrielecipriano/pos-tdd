package usecase;

import org.apache.tools.ant.filters.StringInputStream;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.*;

import java.util.Scanner;

import static java.lang.System.lineSeparator;

public class EntryPointInterpreterTest
{
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private BarcodeController barcodeController;
  private EntryPointInterpreter entryPointInterpreter;

  @Before
  public void setUp() throws Exception
  {
    entryPointInterpreter = new EntryPointInterpreter(barcodeController);
  }

  @Test
  public void oneBarcode() throws Exception
  {
    context.checking(new Expectations()
    {{
      oneOf(barcodeController).onBarcode("BARCODE");
    }});

    entryPointInterpreter.compute(new Scanner(new StringInputStream("BARCODE")));
  }

  @Test
  public void twoBarcode() throws Exception
  {
    context.checking(new Expectations()
    {{
      oneOf(barcodeController).onBarcode("BARCODE1");
      oneOf(barcodeController).onBarcode("BARCODE2");
    }});

    entryPointInterpreter.compute(new Scanner(new StringInputStream("BARCODE1" + lineSeparator()
                                                                  + "BARCODE2")));
  }
}