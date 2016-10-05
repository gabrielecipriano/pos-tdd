package catalog;

import org.junit.Test;
import product.Price;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class InMemoryPricingCatalogTest
{
  @Test
  public void priceFound() throws Exception
  {
    Price price = new Price("KNOWN_BARCODE_PRICE");

    Map<String, Price> priceBarcodeAssociation = new HashMap<String, Price>()
    {{
      put("KNOWN_BARCODE", price);
    }};

    InMemoryPricingCatalog pricingCatalog = new InMemoryPricingCatalog(priceBarcodeAssociation);

    assertThat(pricingCatalog.findPrice("KNOWN_BARCODE").get(), is(price));
  }

  @Test
  public void priceNotFound() throws Exception
  {
    InMemoryPricingCatalog pricingCatalog = new InMemoryPricingCatalog(new HashMap<>());

    assertThat(pricingCatalog.findPrice("UNKOWN_BARCODE").isPresent(), is(false));
  }
}