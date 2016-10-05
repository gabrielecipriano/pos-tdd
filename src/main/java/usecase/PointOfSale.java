package usecase;

import catalog.InMemoryPricingCatalog;
import display.Display;
import display.DriverPoweredDisplay;
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
        new SellOneItem(
            new Display()
            {
              @Override public void productNotFound()
              {
                System.out.println("Product not Found");
              }

              @Override public void productFound(Price price)
              {
                System.out.println(price.getPrice());
              }
            },
            new InMemoryPricingCatalog(
                new HashMap<String, Price>()
                {{
                  put("50415687", new Price("EUR 1.50"));
                }}
            )
        )
    ).compute(new Scanner(System.in));
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
