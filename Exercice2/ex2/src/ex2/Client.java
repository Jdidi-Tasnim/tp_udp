package ex2;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String argv[]) {
        try {
            InetAddress serverAddress = InetAddress.getByName("localhost"); // Adresse IP du serveur
            int serverPort = 12345; // Port sur lequel le serveur écoute
            
            DatagramSocket socket = new DatagramSocket(); // Création d'un socket UDP
            
            Voiture car = new Voiture("SUV", "Toyota"); // Création d'un objet voiture
            
            // Fixer la quantité de carburant de la voiture
            car.setCarburant(30); // Exemple : fixer la quantité de carburant à 30
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream(); // Création d'un flux de sortie pour écrire l'objet
            ObjectOutputStream oos = new ObjectOutputStream(bos); // Création d'un flux de sortie d'objets
            oos.writeObject(car); // Écriture de l'objet dans le flux de sortie
            byte[] data = bos.toByteArray(); // Conversion de l'objet en tableau de bytes
            
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, serverPort); // Création d'un packet à envoyer au serveur
            socket.send(packet); // Envoi du packet au serveur
            
            byte[] buffer = new byte[1024]; // Buffer pour stocker les données reçues
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length); // Création d'un packet pour recevoir la réponse du serveur
            socket.receive(responsePacket); // Réception de la réponse du serveur
            
            ByteArrayInputStream bis = new ByteArrayInputStream(responsePacket.getData()); // Création d'un flux d'entrée à partir des données reçues
            ObjectInputStream ois = new ObjectInputStream(bis); // Création d'un flux d'entrée d'objets
            Voiture updatedCar = (Voiture) ois.readObject(); // Lecture de l'objet voiture modifié
            
            // Affichage de l'état de la voiture après modification
            System.out.println("Carburant après ravitaillement : " + updatedCar.getCarburant() + " litres");
            System.out.println("Capacité du réservoir : " + updatedCar.getCapacite() + " litres");
            
            socket.close(); // Fermeture du socket
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

