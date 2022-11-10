/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pesajepuertoserial;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Scanner;

/**
 *
 * @author Diego Ramos
 */
public class PesajePuertoSerial {

    /**
     * @param args the command line arguments
     */
    public static Double peso=0.0;
    
    public static void main(String[] args) {
          CommPortIdentifier cpi = null;
    SerialPort puertoSerial = null;
    Scanner entrada = null;
    PrintStream salida = null;
    Enumeration comports = CommPortIdentifier.getPortIdentifiers();
    while(comports.hasMoreElements()){
    cpi = (CommPortIdentifier) comports.nextElement();
    if(cpi.getName().equals("COM2")){
        break;
    }
    }
    
        try {
            CommPort puerto = cpi.open("Puerto Serial",2000);
            puertoSerial = (SerialPort) puerto;
            puertoSerial.setSerialPortParams(19200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            entrada = new Scanner(puertoSerial.getInputStream());
            salida = new PrintStream(puertoSerial.getOutputStream());
        } catch (PortInUseException ex) {
            //Logger.getLogger(pesaje.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(pesaje.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedCommOperationException ex) {
            //Logger.getLogger(pesaje.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(!entrada.hasNextDouble()){
            entrada.close();
            entrada = null;
        try {
            entrada = new Scanner(puertoSerial.getInputStream());
        } catch (IOException ex) {
            //Logger.getLogger(pesaje.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
        peso = entrada.nextDouble();
        System.out.println("Peso: "+peso);
//        salida.println("Dato capturado");
    }
    
}
