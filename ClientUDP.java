import java.net.*; // Importation des classes relatives au réseau
import java.util.Scanner; // Importation de la classe Scanner pour la saisie utilisateur

public class ClientUDP { // Définition de la classe principale ClientUDP
    public static void main(String argv[]) { // Définition de la méthode principale
        int port = 0; // Initialisation de la variable port à 0
        String host = ""; // Initialisation de la variable host comme une chaîne vide
        Scanner keyb = new Scanner(System.in); // Création d'un objet Scanner pour la saisie utilisateur

        try { // Début du bloc try-catch pour gérer les exceptions
            // On récupère les paramètres : nom de la machine serveur et numéro de port
            System.out.println("Adress du serveur : ");
            host = keyb.next(); // Saisie de l'adresse du serveur
            System.out.println("port d'écoute du serveur : ");
            port = keyb.nextInt(); // Saisie du port d'écoute du serveur

            InetAddress adr; // Déclaration de la variable adr de type InetAddress
            DatagramPacket packet; // Déclaration de la variable packet de type DatagramPacket
            DatagramSocket socket; // Déclaration de la variable socket de type DatagramSocket

            // adr contient l'@IP de la partie serveur
            adr = InetAddress.getByName(host); // Résolution du nom d'hôte en adresse IP

            // données à envoyer : chaîne de caractères
            byte[] data = (new String("Hello Word")).getBytes(); // Conversion de la chaîne en tableau de bytes

            // création du paquet avec les données et en précisant l'adresse
            // du serveur (@IP et port sur lequel il écoute)
            packet = new DatagramPacket(data, data.length, adr, port); // Création du paquet à envoyer

            // création d'une socket, sans la lier à un port particulier
            socket = new DatagramSocket(); // Création d'une nouvelle socket

            // envoi du paquet via la socket
            socket.send(packet); // Envoi du paquet au serveur

            // création d'un tableau vide pour la réception
            byte[] reponse = new byte[15]; // Initialisation d'un tableau pour stocker la réponse

            packet.setData(reponse); // Configuration du packet pour recevoir les données
            packet.setLength(reponse.length); // Configuration de la longueur du packet

            // attente paquet envoyé sur la socket du client
            socket.receive(packet); // Attente et réception de la réponse du serveur

            // récupération et affichage de la donnée contenue dans le paquet
            String chaine = new String(packet.getData(), 0, packet.getLength()); // Conversion des données reçues en chaîne de caractères
            System.out.println(" reçu du serveur : " + chaine); // Affichage de la réponse du serveur
        } catch (Exception e) { // Capture des exceptions
            System.err.println("Erreur : " + e); // Affichage de l'erreur en cas d'exception
        }
    }
}
