import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        //Este programa toma una mano de poker y nos dice que tipos tiene.
        //H = Hearts  C = Clovers  S = Spades  D = Diamonds

        //Esta es la mano que tiene el jugador.
        ArrayList<String> myHand = new ArrayList<String>();

        myHand.add("2H");
        myHand.add("4H");
        myHand.add("5D");
        myHand.add("9S");
        myHand.add("KC");

        hasPair(myHand);

    }

    //Esta funcion regresa solo los numeros de la mano de poker.
    private static ArrayList<Character> getOnlyNumbers( ArrayList<String> hand ){
        //Esta es una lista que solo contiene los numeros.
        ArrayList<Character> onlyNumbers = new ArrayList<Character>();

        hand.forEach((card) -> {
            onlyNumbers.add(card.charAt(0));
        });

        return onlyNumbers;
    }

    //Checa si hay un par dentro de la mano.
    private static boolean hasPair( ArrayList<String> hand ){

        //Esta lista solo contiene el valor de la carta, no el palo.
        ArrayList<Character> onlyNumbers = getOnlyNumbers(hand);
        onlyNumbers.sort(null);


        for (int i = 0; i < (onlyNumbers.size() - 1); i++) {
            char thisCard = onlyNumbers.get(i);
            char nextCard = onlyNumbers.get(i + 1);

            if (thisCard == nextCard){
                return true;
            }
        }

        return false;
    }


}
