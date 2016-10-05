package usecase;

public class BarcodeInputInterpreter implements InputInterpreter
{
  private final SellOneItem sellOneItem;

  public BarcodeInputInterpreter(SellOneItem sellOneItem)
  {
    this.sellOneItem = sellOneItem;
  }

  @Override public boolean canHandle(String inputText)
  {
    return true;
  }

  @Override public void execute(String inputText)
  {
    sellOneItem.onBarcode(inputText);
  }
}
