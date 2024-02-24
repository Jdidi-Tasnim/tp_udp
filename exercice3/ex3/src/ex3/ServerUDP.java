package ex3;

import java.net.*;
import java.util.Date;



public class ServerUDP {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(1250)) { // Création d'une socket UDP pour écouter sur le port 1250
            while (true) { // Boucle infinie pour écouter en continu
                
                byte[] buffer = new byte[1024]; // Création d'un buffer pour recevoir les données
                
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length); // Création d'un datagramme pour recevoir les données
                
                socket.receive(packet); // Réception du datagramme
                
                InetAddress address = packet.getAddress(); // Récupération de l'adresse de l'émetteur
                int port = packet.getPort(); // Récupération du port de l'émetteur
                
                String date = new Date().toString(); // Création de la date et de l'heure courantes
                
                byte[] responseData = date.getBytes(); // Conversion de la date en tableau de bytes
                
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, address, port); // Création d'un datagramme pour envoyer la date à l'émetteur
                
                socket.send(responsePacket); // Envoi du datagramme contenant la date
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

