package display;

import product.Price;
import printer.PrinterDriver;

public class DriverPoweredDisplay implements Display
{
  private PrinterDriver printerDriver;

  public DriverPoweredDisplay(PrinterDriver printerDriver)
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
