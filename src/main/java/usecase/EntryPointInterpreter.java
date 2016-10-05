package usecase;

import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;

public class EntryPointInterpreter
{
  private List<InputInterpreter> interpreters;

  public EntryPointInterpreter(InputInterpreter... inputInterpreters)
  {
    this.interpreters = asList(inputInterpreters);
  }

  public void compute(Scanner scanner)
  {
    while (scanner.hasNext())
    {
      String inputText = scanner.next();

      for (InputInterpreter interpreter : interpreters)
      {
        if (interpreter.canHandle(inputText))
        {
          interpreter.execute(inputText);
        }
      }
    }
  }
}
