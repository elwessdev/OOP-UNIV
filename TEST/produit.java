import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Produit implements Serializable {
    private String nom;
    private double prix;

    public Produit() {}

    public Produit(String nom, double prix) {
        this.nom = nom;

        this.prix = prix;
    }

    public String getNom() {
        return this.nom;
    }

    public Double getPrix() {
        return this.prix;
    }

    @Override
    public String toString() {
        return nom + " " + prix;
    }

    public static void main(String[] args) {
        // Q2
        ArrayList<Produit> produits = new ArrayList<>(
            Array.asList(
                new Produit("Telephone", 800.0),
                new Produit("Ordinateur", 1200.0),
            )
        );

        // Q3
        try (Writer f1 = new FileWriter("produit.txt")) {
            for (Produit produit : produits) {
                f1.write(produit.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Q4
        try (InputStream input = new FileInputStream("produit.txt")) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.print((char) n);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Q5
        try (OutputStream output = new FileOutputStream("produit.txt")) {
            for (Produit produit : produits) {
                output.write((produit.toString() + "\n").getBytes());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Q6 - Serialization
        try (
            OutputStream o1 = Files.newOutputStream(Paths.get("produits.ser"));
            ObjectOutputStream ob = new ObjectOutputStream(o1)
        ) {
            ob.writeObject(produits);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Q7 - Deserialization
        try (
            InputStream inpt = Files.newInputStream(Paths.get("produits.ser"));
            ObjectInputStream obs = new ObjectInputStream(inpt)
        ) {
            ArrayList<Produit> ls = (ArrayList<Produit>) obs.readObject();
            for (Produit produit : ls) {
                System.out.println(produit);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}