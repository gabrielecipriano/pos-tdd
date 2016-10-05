package printer;

import java.io.IOException;
import java.net.*;

public class UdpPrinterDriver implements PrinterDriver
{
  private InetAddress serverIp;
  private int port;

  public UdpPrinterDriver(int port, InetAddress serverIp)
  {
    this.port = port;
    this.serverIp = serverIp;
  }

  public void print(String text)
  {
    try
    {
      DatagramSocket clientSocket = new DatagramSocket();

      byte[] sendData = text.getBytes();

      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIp, port);

      clientSocket.send(sendPacket);
    }
    catch (IOException e)
    {
      throw new RuntimeException();
    }

  }
}
