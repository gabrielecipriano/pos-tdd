package display;

import product.Price;
import printer.PrinterDriver;

public class UdpDisplay implements Display
{
  private PrinterDriver printerDriver;

  public UdpDisplay(PrinterDriver printerDriver)
  {
    this.printerDriver = printerDriver;
  }

  @Override public void productNotFound()
  {
    printerDriver.print("Product not found");
  }

  @Override public void productFound(Price price)
  {
    printerDriver.print(price.getPrice());
  }
}
