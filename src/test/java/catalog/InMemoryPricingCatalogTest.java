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
    Map<String, String> priceBarcodeAssociation = new HashMap<String, String>()
    {{
      put("KNOWN_BARCODE", "KNOWN_BARCODE_PRICE");
    }};

    InMemoryPricingCatalog pricingCatalog = new InMemoryPricingCatalog(priceBarcodeAssociation);

    assertThat(pricingCatalog.findPrice("KNOWN_BARCODE").get(), is(new Price("KNOWN_BARCODE_PRICE")));
  }

  @Test
  public void priceNotFound() throws Exception
  {
    InMemoryPricingCatalog pricingCatalog = new InMemoryPricingCatalog(new HashMap<>());

    assertThat(pricingCatalog.findPrice("UNKOWN_BARCODE").isPresent(), is(false));
  }
}