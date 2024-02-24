import java.net.*; // Importation des classes relatives au réseau
import java.util.Scanner; // Importation de la classe Scanner pour la saisie utilisateur

public class ServeurUDP { // Définition de la classe principale ServeurUDP
    public static void main(String argv[]) { // Définition de la méthode principale
        int port = 0; // Initialisation de la variable port à 0
        Scanner keyb = new Scanner(System.in); // Création d'un objet Scanner pour la saisie utilisateur

        try { // Début du bloc try-catch pour gérer les exceptions
            // on récupère le paramètre : port d'écoute
            System.out.println("port d'écoute : ");
            port = keyb.nextInt(); // Saisie du port d'écoute

            DatagramPacket packet; // Déclaration de la variable packet de type DatagramPacket

            // création d'une socket liée au port précisé en paramètre
            DatagramSocket socket = new DatagramSocket(port); // Création d'une socket associée au port spécifié

            // tableau de 15 octets qui contiendra les données reçues
            byte[] data = new byte[15]; // Initialisation d'un tableau de bytes pour stocker les données reçues

            // création d'un paquet en utilisant le tableau d'octets
            packet = new DatagramPacket(data, data.length); // Création d'un DatagramPacket pour recevoir les données

            // attente de la réception d'un paquet. Le paquet reçu est placé
            // dans packet et ses données dans data.
            socket.receive(packet); // Attente et réception d'un paquet

            // récupération et affichage des données (une chaîne de caractères)
            String chaine = new String(packet.getData(), 0, packet.getLength()); // Conversion des données reçues en chaîne de caractères
            System.out.println(" recu : " + chaine); // Affichage des données reçues

            // Affichage de l'adresse IP et du port de l'expéditeur du paquet
            System.out.println(" ca vient de : " + packet.getAddress() + ":" + packet.getPort());

            // on met une nouvelle donnée dans le paquet
            // (qui contient donc le couple @IP/port de la socket côté client)
            byte[] reponse = (new String("bien recu")).getBytes(); // Conversion de la chaîne en tableau de bytes
            packet.setData(reponse); // Configuration des données du paquet avec la nouvelle réponse
            packet.setLength(reponse.length); // Configuration de la longueur du paquet

            // on envoie le paquet au client
            socket.send(packet); // Envoi du paquet de réponse au client
        } catch (Exception e) { // Capture des exceptions
            System.err.println("Erreur : " + e); // Affichage de l'erreur en cas d'exception
        }
    }
}
