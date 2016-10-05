package catalog;

import product.Price;

import java.util.*;

public class InMemoryPricingCatalog implements PricingCatalog
{
  private Map<String, Price> priceBarcodeAssociation;

  public InMemoryPricingCatalog(Map<String, Price> priceBarcodeAssociation)
  {
    this.priceBarcodeAssociation = priceBarcodeAssociation;
  }

  @Override public Optional<Price> findPrice(String barcode)
  {
    if (priceBarcodeAssociation.containsKey(barcode))
    {
      return Optional.of(priceBarcodeAssociation.get(barcode));
    }

    return Optional.empty();
  }
}
