package catalog;

import product.Price;

import java.util.Optional;

public interface PricingCatalog
{
  Optional<Price> findPrice(String barcode);
}
