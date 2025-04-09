import java.io.*; // FileStream, ObjectStream, FileWriter
import java.nio.file.*; // Paths, Files
import java.util.*; // ArrayList

public class Livre implements Serializable {
    private String titre;
    private String auteur;
    private double prix;

    public Livre(){}
    public Livre(String titre, String auteur, double prix){
        this.titre = titre;
        this.auteur = auteur;
        this.prix = prix;
    }
    public String getTitre(){
        return this.titre;
    }

    public String getAuteur(){
        return auteur;
    }
    public double getPrix(double prix){
        return this.prix;
    }

    @Override
    public String toString(){
        return titre+" "+auteur+" "+prix;
    }

    public static void main(String[] args){
        // Q2
        ArrayList<Livre> livers = new ArrayList<>(
            Arrays.asList(
            new Livre("Le Petit Prince","Antoine de Saint-Exupéry",10.0),
            new Livre("1984","George Orwell",15.0),
            new Livre("La Peste","Albert Camus",12.0)
            )
        );

        // Q3
        try(
            Writer f = new FileWriter("livres.txt");
        ){
            for(Livre livre:livers){
                f.write(livre.toString());
            }
        }catch(IOException e){
            System.out.println(e);
        }
        
        // Q4
        try(InputStream inp = new FileInputStream("livres.txt")){
            int n;
            while((n = inp.read()) != -1){
                System.out.println((char)n);
            }
        } catch(IOException e){
            System.out.println(e);
        }

        // Q5
        try(OutputStream out = new FileOutputStream("livres.txt")){
            for(Livre livre:livers){
                out.write(livre.toString());
            }
        } catch(IOException e){
            System.out.println(e);
        }
        
        // Q6
        try(
            OutputStream f = Files.newOutputStream(Paths.get("livres.ser"));
            ObjectOutputStream ob = new ObjectOutputStream(f);
        ){
            ob.writeObject(livres);
        } catch(IOException e){
            System.out.println(e);
        }
        // Q7
        try(
            InputStream f = Files.newInputStream(Paths.get("livres.ser"));
            ObjectInputStream ob = new ObjectInputStream(f);
        ){
            ArrayList<Livre> livres = (ArrayList<Livre>) ob.readObject();
            for(Livre livre:livres){
                System.out.println((Livre)livre);
            }
        } catch(IOException | ClassNotFoundException e){
            System.out.println(e);
        }
    }
}