package usecase;

public interface InputInterpreter
{
  boolean canHandle(String inputText);

  void execute(String inputText);
}
