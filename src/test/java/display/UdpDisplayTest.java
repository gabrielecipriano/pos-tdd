package display;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.*;
import product.Price;
import printer.PrinterDriver;

public class UdpDisplayTest
{
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private PrinterDriver printerDriver;
  private UdpDisplay udpDisplay;

  @Before
  public void setUp() throws Exception
  {
    udpDisplay = new UdpDisplay(printerDriver);
  }

  @Test
  public void productFound() throws Exception
  {
    context.checking(new Expectations()
    {{
      oneOf(printerDriver).print("EUR 18.50");
    }});

    udpDisplay.productFound(new Price("EUR 18.50"));
  }

  @Test
  public void productNotFound() throws Exception
  {
    context.checking(new Expectations()
    {{
      oneOf(printerDriver).print("Product not found");
    }});

    udpDisplay.productNotFound();
  }
}