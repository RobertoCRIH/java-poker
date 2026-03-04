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
        myHand.add("QS");
        myHand.add("QC");

        myHand.sort(null);

        System.out.println(hasThreeOfKind(myHand));

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

    //Chaca si hay dos pares en la mano. Regresa una lista de 4 cartas.
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

    private static ArrayList<String> hasThreeOfKind( ArrayList<String> hand){
        ArrayList<String> response = new ArrayList<String>();

        hand.sort(null);

        for (int i = 0; i < (hand.size() - 2 ); i++) {
            String thisCard = hand.get(i);
            String nextCard = hand.get(i+1);
            String nextNextCard = hand.get(i+2);

            if ( (thisCard.charAt(0) == nextCard.charAt(0)) && (thisCard.charAt(0) == nextNextCard.charAt(0)) ){
                response.add(thisCard);
                response.add(nextCard);
                response.add(nextNextCard);

                return response;
            }
        }

        return response;
    }


}
