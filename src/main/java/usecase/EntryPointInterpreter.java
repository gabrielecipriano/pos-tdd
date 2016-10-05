package usecase;

import java.util.Scanner;

public class EntryPointInterpreter
{
  private BarcodeController barcodeController;

  public EntryPointInterpreter(BarcodeController barcodeController)
  {
    this.barcodeController = barcodeController;
  }

  public void compute(Scanner scanner)
  {
    while(scanner.hasNext())
    {
      barcodeController.onBarcode(scanner.next());
    }
  }
}
