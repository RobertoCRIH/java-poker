import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        //Este programa toma una mano de poker y nos dice que tipos tiene.
        //H = Hearts  C = Clovers  S = Spades  D = Diamonds

        //Esta es la mano que tiene el jugador.
        ArrayList<String> myHand = new ArrayList<String>();

        myHand.add("2H");
        myHand.add("4H");
        myHand.add("QD");
        myHand.add("2S");
        myHand.add("QC");

        myHand.sort(null);

        System.out.println(hasTwoPair(myHand));

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

    //Checa si hay un par dentro de la mano. Regresa las dos cartas que conforman el par
    private static ArrayList<String> hasPair( ArrayList<String> hand ){
        //La respuesta que se va a regresar
        ArrayList<String> response = new ArrayList<String>();

        //Vamos uno por uno comparando laa carta con la siguiente, como estan acomodados, si hay dos iguales, can a estar
        //uno al lado del otro.
        for (int i = 0; i < (hand.size() - 1); i++) {
            String thisCard = hand.get(i);
            String nextCard = hand.get(i + 1);

            //Si son iguales...
            if (thisCard.charAt(0) == nextCard.charAt(0)){
                //Acomodamos la mano para que sea igual que la mano con solo numeros.
                hand.sort(null);

                //Agregamos las cartas a la respuesta.
                response.add( thisCard );
                response.add( nextCard );

                return response;
            }
        }

        return response;
    }

    private static ArrayList<String> hasTwoPair( ArrayList<String> hand ){

        ArrayList<String> response = new ArrayList<String>();

        //Conseguimos el primer par.
        ArrayList<String> firstPair = hasPair(hand);

        //Aqui checamos si hubo un primer par. Si no, regresa la lista vacia.
        if (firstPair.isEmpty()){
            return response;
        }

        hand.removeAll(firstPair);
        //System.out.println("CARDS LEFT: " + hand);

        ArrayList<String> secondPair = hasPair(hand);

        //Si el segundo par tambien existe se agregan ambos pares a la respuesta y se regresa.
        if( !secondPair.isEmpty() ){
            response.add(firstPair.get(0));
            response.add(firstPair.get(1));

            response.add(secondPair.get(0));
            response.add(secondPair.get(1));
        }

        return response;
    }


}
