package display;

import product.Price;

public interface Display
{
  void productNotFound();

  void productFound(Price price);
}
