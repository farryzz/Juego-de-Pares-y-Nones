package udpServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPCliente_1 {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		DatagramSocket clientSocket = new DatagramSocket();// socket cliente

		// DATOS DEL SERVIDOR al que enviar mensaje
		InetAddress IPServidor = InetAddress.getLocalHost();// localhost
		int puerto = 12345; // puerto por el que escucha
		int contC=0;
		int contS=0;
		
		while(contC<3 && contS<3) {
		// INTRODUCIR DATOS POR TECLADO
		System.out.print("Quieres jugar pares o nones ");
		String cadena = sc.nextLine();
		System.out.println("Dime un numero ");
		String num = sc.nextLine();
		int numJ = Integer.parseInt(num);
		
		byte[] enviados = new byte[1024];
		enviados = cadena.getBytes();
		byte[] enviados2 = new byte[1024];
		enviados2 = num.getBytes();

		// ENVIANDO DATAGRAMA AL SERVIDOR
		DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
		clientSocket.send(envio);
		
		DatagramPacket envio2 = new DatagramPacket(enviados2, enviados2.length, IPServidor, puerto);
		clientSocket.send(envio2);

		// RECIBIENDO DATAGRAMA DEL SERVIDOR
		byte[] recibidos = new byte[2];
		DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
		clientSocket.receive(recibo);

		//Obtengo el numero del Servidor
		byte[] hh = recibo.getData();
		int numero = hh[0];
		System.out.println("El juega "+numero);
		if(numJ%2==0) {
			if(numero%2==0) {
				contC++;
				System.out.println("He ganado. Vamos Servidor: "+contS+" Cliente: "+contC);
			}else {
				contS++;
				System.out.println("He perdido. Vamos Servidor: "+contS+" Cliente: "+contC);
			}
		}else {
			if(numero%2==0) {
				contS++;
				System.out.println("He perdido. Vamos Servidor: "+contS+" Cliente: "+contC);
			}else {
				contC++;
				System.out.println("He ganado. Vamos Servidor: "+contS+" Cliente: "+contC);
			}
		}
		
		}
		System.out.println("FIN DE LA PARTIDA");
		clientSocket.close();// cerrar socket
	}

}
