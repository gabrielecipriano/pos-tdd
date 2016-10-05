package display;

import product.Price;
import printer.PrinterClient;

public class UdpDisplay implements Display
{
  private PrinterClient printerClient;

  public UdpDisplay(PrinterClient printerClient)
  {
    this.printerClient = printerClient;
  }

  @Override public void productNotFound()
  {
    printerClient.print("Product not found");
  }

  @Override public void productFound(Price price)
  {
    printerClient.print(price.getPrice());
  }
}
