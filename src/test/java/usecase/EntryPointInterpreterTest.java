package usecase;

import org.apache.tools.ant.filters.StringInputStream;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.*;

import java.util.Scanner;

import static java.lang.System.lineSeparator;
import static java.util.Arrays.asList;

public class EntryPointInterpreterTest
{
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private InputInterpreter firstInputInterpreter;

  @Mock
  private InputInterpreter secondInputInterpreter;

  private EntryPointInterpreter entryPointInterpreter;

  @Before
  public void setUp() throws Exception
  {
    entryPointInterpreter = new EntryPointInterpreter(firstInputInterpreter, secondInputInterpreter);
  }

  @Test
  public void onlyOneCanHandle() throws Exception
  {
    context.checking(new Expectations()
    {{
      allowing(firstInputInterpreter).canHandle("BARCODE");
      will(returnValue(true));

      allowing(secondInputInterpreter).canHandle("BARCODE");
      will(returnValue(false));

      oneOf(firstInputInterpreter).execute("BARCODE");
    }});

    entryPointInterpreter.compute(new Scanner(new StringInputStream("BARCODE")));
  }

  @Test
  public void bothCanHandle() throws Exception
  {
    context.checking(new Expectations()
    {{
      allowing(firstInputInterpreter).canHandle("BARCODE");
      will(returnValue(true));

      allowing(secondInputInterpreter).canHandle("BARCODE");
      will(returnValue(true));

      oneOf(firstInputInterpreter).execute("BARCODE");
      oneOf(secondInputInterpreter).execute("BARCODE");
    }});

    entryPointInterpreter.compute(new Scanner(new StringInputStream("BARCODE")));
  }

  @Test
  public void multipleInputs() throws Exception
  {
    EntryPointInterpreter entryPointInterpreter = new EntryPointInterpreter(firstInputInterpreter);
    context.checking(new Expectations()
    {{
      allowing(firstInputInterpreter).canHandle("BARCODE1");
      will(returnValue(true));

      allowing(firstInputInterpreter).canHandle("BARCODE2");
      will(returnValue(false));

      oneOf(firstInputInterpreter).execute("BARCODE1");
    }});

    entryPointInterpreter.compute(new Scanner(new StringInputStream("BARCODE1"
                                                                        + lineSeparator()
                                                                        + "BARCODE2")));
  }
}