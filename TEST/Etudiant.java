import java.util.*;
import java.io.*;
import java.nio.file.*;

// Q1
public class Etudiant implements Serializable{
    private String nom;
    private String prenom;
    private Srting matricule;
    private double noteMoyenne;

    public Etudiant(){}
    public Etudiant(String nom,String prenom, String matricule,double noteMoyenne){
        this.nom=nom;
        this.prenom=prenom;
        this.matricule=matricule;
        this.noteMoyenne=noteMoyenne;
    }

    public String getnom(){
        return nom;
    }

    public String getprenom(){
        return prenom
    }
    
    public String getmatricule(){
        return matricule;
    }
    
    public double noteMoyenne(){
        return noteMoyenne;
    }
    @Override
    public String toString(){
        return nom+" "+prenom+" "+matricule+" "+noteMoyenne;
    }

    public static void main(String args[]){
        // Q2
        ArrayList<Etudiant> Etud= new ArrayList<>(
            Arrays.asList(
                new Etudiant("Ahmed","ben ali", "E001",15.5),
                new Etudiant("Fatma ","Trabelsi","E002",13.2),
                new Etudiant("Omar Bouzid"," Bouzid", "E003",17.8),
                new Etudiant("Hana Jebali","Jebali", "E004",12.4),
                new Etudiant("Mehdi Gharbi","Gharbi", "E005",16.0)
            )
        );
        
        // Q3
        try(
            Writer f= new FileWriter("Etudiant.txt");
        ){
            for(Etudiant Etud:Etudiants ){
                f.write(Etud.toString())
            }
        } catch(IOException e){
            System.out.println(e);
        }
        
        // Q4
        try(
            InputStream inp = new FileInputStream("Etudiant.txt")
        ){
            int n;
            while((n=inp.read())!= -1){
                System.out.println((char)n);
            }
        } catch(IoException e){
                System.out.println(e.getmessage());
        };

        // Q5
        try(
            OutputStream ops = new FileOutputStream("Etudiant.txt")
        ){
            for(Etudiant Etud:Etudiants){
                ops.write(Etud.toString());
            }
        } catch(IOException e){
            System.out.println(e.getmessage());
        };
        
        // Q6
        try(
            OutputStream fo = Files.newOutputStream(Paths.get("Etudiant.ser"));
            ObjectoutputStream obj = new ObjectoutputStream(fo);
        ){
            obj.write(Etud);
        } catch(IOException e){
            System.out.println(e.getmessage())
        };
        
        // Q7
        try(
            InputStream inpp = Files.newInputstream(Paths.get("Etudiant.ser"));
            ObjectInputStream ois = new ObjectInputStream(inpp)
        ){
            ArrayList<Etudiant> arl = (ArrayList<Etudiant>) ois.readObject();
            for (Etudiant Etud:arl){
                System.out.println(Etud);
            }
        } catch(IOException e){
            System.out.println(e.getmessage());
        };
    }
}