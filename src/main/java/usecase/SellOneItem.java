package usecase;

import catalog.PricingCatalog;
import display.*;
import product.Price;

import java.util.Optional;

public class SellOneItem
{
  private Display textDisplay;
  private PricingCatalog pricingCatalog;

  public SellOneItem(Display textDisplay, PricingCatalog pricingCatalog)
  {
    this.textDisplay = textDisplay;
    this.pricingCatalog = pricingCatalog;
  }

  public void onBarcode(String barcode)
  {
    Optional<Price> eventualProduct = pricingCatalog.findPrice(barcode);

    if (!eventualProduct.isPresent())
    {
      textDisplay.productNotFound();
    }
    else
    {
      textDisplay.productFound(eventualProduct.get());
    }
  }
}
