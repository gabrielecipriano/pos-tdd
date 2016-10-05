package usecase;

import org.junit.Test;
import printer.UdpPrinterDriver;

import java.io.IOException;
import java.net.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UdpPrinterDriverTest
{
  @Test
  public void clientTalksToServer() throws Exception
  {
    DatagramSocket serverSocket = new DatagramSocket(9876);

    UdpPrinterDriver udpPrinterClient = new UdpPrinterDriver(9876, ipFor("localhost"));

    udpPrinterClient.print("IRRELEVANT_TEXT");

    assertThat(receivedBy(serverSocket), is("IRRELEVANT_TEXT"));
  }

  private String receivedBy(DatagramSocket serverSocket)
  {
    while (true)
    {
      byte[] receiveData = new byte[1024];

      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

      try
      {
        serverSocket.receive(receivePacket);

        return new String(receiveData, 0, receivePacket.getLength());
      }
      catch (IOException e)
      {
        throw new RuntimeException();
      }
    }
  }

  private InetAddress ipFor(String localhost)
  {
    try
    {
      return InetAddress.getByName(localhost);
    }
    catch (UnknownHostException e)
    {
      throw new RuntimeException();
    }
  }
}