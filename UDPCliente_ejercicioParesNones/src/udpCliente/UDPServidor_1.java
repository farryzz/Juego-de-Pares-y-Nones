package udpCliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServidor_1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Asocio el socket al puerto 12345
		int contC = 0;
		int contS = 0;
		DatagramSocket socket = new DatagramSocket(12345);
		System.out.println("Servidor Esperando Datagrama .......... ");
		
		
		while(contC<3 && contS<3){
		byte[] bufer = new byte[1024]; // para recibir el datagrama
		DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);
		socket.receive(recibo); //recibo datagrama	

	    String mensaje = new String(recibo.getData()).trim();// obtengo String

		System.out.println("El juega:" + mensaje);
		int al = (int) Math.floor((Math.random() * 100) + 1);
		System.out.println("Se ha generado un numero aleatorio: "+al);
		
		bufer = new byte[1024];
		DatagramPacket recibo2 = new DatagramPacket(bufer, bufer.length);
		socket.receive(recibo2);
		String num = new String(recibo2.getData()).trim();
		int numJ = Integer.parseInt(num);
		
		if(numJ%2==0) {
			if(al%2==0) {
				contC++;
				System.out.println("He perdido. Vamos Servidor: "+contS+" Cliente: "+contC);
			}else {
				contS++;
				System.out.println("He ganado. Vamos Servidor: "+contS+" Cliente: "+contC);
			}
		}else {
			if(al%2==0) {
				contS++;
				System.out.println("He ganado. Vamos Servidor: "+contS+" Cliente: "+contC);
			}else {
				contC++;
				System.out.println("He perdido. Vamos Servidor: "+contS+" Cliente: "+contC);
			}
		}
		
	
		
		// DIRECCION ORIGEN DEL MENSAJE
		InetAddress IPOrigen = recibo2.getAddress();
		int puerto = recibo2.getPort();

		// ENVIANDO DATAGRAMA AL CLIENTE
		
		byte b = (byte) al; // paso entero a byte
		byte[] enviados = new byte[2];
		enviados[0] = b;
		
		DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
		socket.send(envio);
		}
		// CERRAR STREAMS Y SOCKETS
		System.out.println("Cerrando conexi�n...");
		socket.close();
	}

}
