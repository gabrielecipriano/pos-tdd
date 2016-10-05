import catalog.PricingCatalog;
import display.Display;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.*;
import product.Price;
import usecase.SellOneItem;

import java.util.Optional;

public class SellOneItemTest
{
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private Display display;

  @Mock
  private PricingCatalog pricingCatalog;

  private SellOneItem sellOneItem;

  @Before
  public void setUp()
  {
    sellOneItem = new SellOneItem(display, pricingCatalog);
  }

  @Test
  public void priceNotFound() throws Exception
  {
    context.checking(new Expectations()
    {{
      allowing(pricingCatalog).findPrice("12345679");
      will(returnValue(Optional.empty()));

      oneOf(display).productNotFound();
    }});

    sellOneItem.onBarcode("12345679");
  }

  @Test
  public void priceFound() throws Exception
  {
    context.checking(new Expectations()
    {{
      allowing(pricingCatalog).findPrice("50415687");
      will(returnValue(Optional.of(new Price("EUR 1.50"))));

      oneOf(display).productFound(new Price("EUR 1.50"));
    }});

    sellOneItem.onBarcode("50415687");
  }
}
