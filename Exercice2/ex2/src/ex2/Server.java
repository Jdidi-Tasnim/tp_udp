package ex2;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String argv[]) {
        try {
            int port = 12345; // Port sur lequel le serveur écoute
            
            DatagramSocket socket = new DatagramSocket(port); // Création d'un socket UDP sur le port spécifié
            
            byte[] buffer = new byte[1024]; // Buffer pour stocker les données reçues
            
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length); // Création d'un packet pour recevoir les données
                socket.receive(packet); // Réception du packet
                
                ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData()); // Création d'un flux d'entrée à partir des données reçues
                ObjectInputStream ois = new ObjectInputStream(bis); // Création d'un flux d'entrée d'objets
                
                Voiture car = (Voiture) ois.readObject(); // Lecture de l'objet voiture
                
                // Traitement de l'objet voiture (par exemple, fixer la quantité de carburant)
                car.setCarburant(50); // Exemple : fixer la quantité de carburant à 50
                
                ByteArrayOutputStream bos = new ByteArrayOutputStream(); // Création d'un flux de sortie pour écrire l'objet modifié
                ObjectOutputStream oos = new ObjectOutputStream(bos); // Création d'un flux de sortie d'objets
                
                oos.writeObject(car); // Écriture de l'objet modifié dans le flux de sortie
                byte[] data = bos.toByteArray(); // Conversion de l'objet en tableau de bytes
                
                InetAddress address = packet.getAddress(); // Adresse IP du client
                int clientPort = packet.getPort(); // Port du client
                
                DatagramPacket responsePacket = new DatagramPacket(data, data.length, address, clientPort); // Création d'un packet de réponse
                socket.send(responsePacket); // Envoi de la réponse au client
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
