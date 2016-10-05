package catalog;

import product.Price;

import java.util.*;

public class InMemoryPricingCatalog implements PricingCatalog
{
  private Map<String, String> priceBarcodeAssociation;

  public InMemoryPricingCatalog(Map<String, String> priceBarcodeAssociation)
  {
    this.priceBarcodeAssociation = priceBarcodeAssociation;
  }

  @Override public Optional<Price> findPrice(String barcode)
  {
    if (priceBarcodeAssociation.containsKey(barcode))
    {
      return Optional.of(new Price(priceBarcodeAssociation.get(barcode)));
    }

    return Optional.empty();
  }
}
