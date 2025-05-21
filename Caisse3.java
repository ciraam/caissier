import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Caisse3 {
    static int etatMenu;
    static boolean etatPoste = false;
    static float caisse;
    static int[][] detailCaisse;

    public static void main(String[] args){
        menu();
    }

    public static void menu(){
        Scanner scannerMenu = new Scanner(System.in);
        System.out.println("Pour accéder au menu taper 'r' ou 'R', sinon taper autre chose"); 
        String accesMenu = scannerMenu.nextLine();
        if (accesMenu.toUpperCase().equals("R")){
            System.out.println("Pour prendre sa prise de poste taper 1, pour effectuer une vente taper 2, pour clôturer sa prise de poste taper 3 et pour quitter le menu taper 4");
            System.out.println("Votre caisse contient : " + caisse + " euros");
            System.out.println("Taper 5 pour afficher les détails de la caisse");
            String menu = scannerMenu.nextLine();
            if (menu.equals("1")){
                etatMenu = 1;
            } else if (menu.equals("2")){
                etatMenu = 2;
            } else if (menu.equals("3")){
                etatMenu = 3;
            } else if (menu.equals("4")){
                etatMenu = 4;
            } else if (menu.equals("5")){
                etatMenu = 5;
                if (caisse != 0){
                    for (int i = 0; i < detailCaisse.length; i++) {
                        System.out.println("Nombre pièce(s)/billet(s) : " + detailCaisse[i][0] + " de " + (float)detailCaisse[i][1] / 100+ " euro(s) soit " + (float) (detailCaisse[i][0] * detailCaisse[i][1]) / 100 + " euro(s)");
                    }
                } else {
                    System.out.println("Caisse vide !");
                }
            } else {
                System.out.println("Seulement 1, 2, 3, 4 ou 5 !");
            }
        }
        if (etatMenu == 1){
            priseDePoste();
        } else if (etatMenu == 2){
            vente();
        } else if (etatMenu == 3){
            clotureDePoste();
        } else if (etatMenu == 4){
            System.exit(0);
        }
        menu();   
    }

    public static void priseDePoste(){
        if (etatPoste == false){
            caisse = 1000;
            // {nbrPiece/Billet, Piece/Billet}
            detailCaisse = new int[][]{{0,20000},{0,10000},{7,5000},{10,2000},{14,1000},{20,500},{50, 200}, {50, 100}, {50, 50}, {100, 20}, {100, 10}, {70, 5}, {50, 2},{50, 1}};
            System.out.println("Bonne journée et bon courage !");
            etatPoste = true;
        } else {
            System.out.println("Déja en service, veuillez clôturer votre poste avant !");
        }
        menu();
    }
    
    public static void clotureDePoste(){
        if (etatPoste == true){
            System.out.println("---------------------------------------------");
            System.out.println("Contenu de la caisse : " + caisse + " euros");
            System.out.println("---------------------------------------------");
            System.out.println("Contenu détaillé de la caisse :");
            System.out.println(" ");
            for (int[] element : detailCaisse) {
                System.out.println("Nombre de pièces/billets : " + element[0]);
                System.out.println("Valeur pièce/billet : " + (float)element[1] / 100 + " euros");
                System.out.println(" ");
            }
            System.out.println("Merci pour votre travail et bonne soirée !");
            caisse = 0;
            detailCaisse = new int[0][0];
            etatPoste = false;
        } else {
            System.out.println("Veuillez faire prise de poste avant !");
        }
        menu();
    }

    public static void vente(){
        if (etatPoste == true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("---------------------------------------------");
            System.out.println("Entrer le montant à payer");
            float montantAPayer = scanner.nextFloat();
            System.out.println("---------------------------------------------");
            System.out.println("Entrer le montant payé par le client");
            float montantRegle = scanner.nextFloat();
            float aRendre = montantRegle - montantAPayer;
            caisse += montantAPayer;
            renduOptimal(aRendre);
        } else {
            System.out.println("Veuillez faire prise de poste avant !");
        }
        menu();
    }

    public static void renduOptimal(float s){
        double somme = Math.round(s * 100.0) / 100.0;
        System.out.println("---------------------------------------------");
        System.out.println("Montant à rendre : " + somme + " euros");
        double conversionEnCentime = somme * 100;
        int conversionEnCentimeEntier = (int)conversionEnCentime;
        ArrayList<Integer> monnaie = new ArrayList<>();
        int[] centimes = {20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1};
        for (int valeur : centimes) {
            int nombrePieceBillet = conversionEnCentimeEntier / valeur;
            monnaie.add(nombrePieceBillet);
            if (nombrePieceBillet != 0) {
                for (int i = 0; i < detailCaisse.length; i++){
                    if (detailCaisse[i][1] == valeur){
                        detailCaisse[i][0] -= nombrePieceBillet;
                    }
                }
            }
            conversionEnCentimeEntier %= valeur;
        }
        System.out.println("---------------------------------------------");
        System.out.println("Montant à rendre détaillé : ");
        System.out.println(" ");
        for (int i = 0; monnaie.size() > i; ++i){
            if (monnaie.get(i) != 0){
                if (centimes[i]/100 != 0){
                    System.out.println(monnaie.get(i) + " pièce(s)/billet(s) de " + centimes[i]/100 + " euro(s)");
                } else {
                    System.out.println(monnaie.get(i) + " pièce(s)/billet(s) de " + (float)centimes[i]/100 + " euro(s)");
                }
            }
        }     
    }
}


