package display;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.*;
import product.Price;
import printer.PrinterDriver;

public class DriverPoweredDisplayTest
{
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private PrinterDriver printerDriver;
  private DriverPoweredDisplay driverPoweredDisplay;

  @Before
  public void setUp() throws Exception
  {
    driverPoweredDisplay = new DriverPoweredDisplay(printerDriver);
  }

  @Test
  public void productFound() throws Exception
  {
    context.checking(new Expectations()
    {{
      oneOf(printerDriver).print("EUR 18.50");
    }});

    driverPoweredDisplay.productFound(new Price("EUR 18.50"));
  }

  @Test
  public void productNotFound() throws Exception
  {
    context.checking(new Expectations()
    {{
      oneOf(printerDriver).print("Product not found");
    }});

    driverPoweredDisplay.productNotFound();
  }
}