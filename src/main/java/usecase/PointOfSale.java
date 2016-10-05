package usecase;

import catalog.InMemoryPricingCatalog;
import display.Display;
import display.DriverPoweredDisplay;
import printer.PrinterDriver;
import printer.UdpPrinterDriver;
import product.Price;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Scanner;

public class PointOfSale
{
  public static void main(String[] args)
  {
    new EntryPointInterpreter(
        barcodeInterpreter()
    ).compute(new Scanner(System.in));
  }

  private static BarcodeInputInterpreter barcodeInterpreter()
  {
    return new BarcodeInputInterpreter(
        new SellOneItem(
            new DriverPoweredDisplay(
                systemOutPrinterDriver()),
            new InMemoryPricingCatalog(
                new HashMap<String, Price>()
                {{
                  put("50415687", new Price("EUR 1.50"));
                }}
            )
    ));
  }

  private static PrinterDriver udpPrinterDriver()
  {
    return new UdpPrinterDriver(5668, localhost());
  }

  private static PrinterDriver systemOutPrinterDriver()
  {
    return System.out::println;
  }

  private static InetAddress localhost()
  {
    try
    {
      return InetAddress.getLocalHost();
    }
    catch (UnknownHostException e)
    {
      e.printStackTrace();
      return null;
    }
  }
}
