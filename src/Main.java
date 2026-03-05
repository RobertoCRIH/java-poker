import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        //Este programa toma una mano de poker y nos dice que tipos tiene.
        //H = Hearts  C = Clovers  S = Spades  D = Diamonds
        //J = Joto  Q = Cuina  R = Rey  D = Diez

        //Esta es la mano que tiene el jugador.
        ArrayList<String> myHand = new ArrayList<String>();

        myHand.add("2H");
        myHand.add("DH");
        myHand.add("RH");
        myHand.add("DH");
        myHand.add("RC");

        myHand.sort(null);
        System.out.println(hasFullHouse(myHand));

    }


    ///Checa si hay un par dentro de la mano. Regresa las dos cartas que conforman el par
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

    ///Checa si hay dos pares en la mano. Regresa una lista de 4 cartas.
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

    ///Checa si hay una tercia. Regresa una lista con las tres cartas.
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

    ///Checa si tiene un straight y regresa un array de cinco cartas.
    private static ArrayList<String> hasStraight( ArrayList<String> hand ){
        //Este es el orden de la mano straight
        String straightOrder = "23456789DJQR";

        //Guardamos si la mano tiene un As o no.
        boolean hasAce = false;

        ArrayList<String> response = new ArrayList<String>();

        //Acomodamos la mano segun orden alfabetico
        hand.sort(null);

        //Convertimos la mano en un string para compararlo con el orden.
        String handString = "";

        //Agregamos todos los valores dentro del string.
        for (int i = 0; i < hand.size(); i++) {
            char card = hand.get(i).charAt(0);

            //Si tenemos un As no se agrega a la mano.
            //El As lo checamos luego, porque puede ubicarse al principio o final del string.
            if (card != 'A'){
                handString = handString + card ;
            } else {
                hasAce = true;
            }

        }

        if (hasAce){
            if ( (handString.equals("2345")) || (handString.equals("DJQR")) ){
                System.out.println("Straight with Ace!");
                response = hand;
                return response;
            }
        } else if (straightOrder.contains(handString)) {
            System.out.println("Straight!");
            response = hand;
        }

        return response;

    }

    /// Checa si todas las cartas son del mismo palo. Regresa una lista de cinco si la respuesta es positiva.
    private static ArrayList<String> hasFlush( ArrayList<String> hand ){
        //Tomamos el tipo de la primera carta y comparamos que los demas sean iguales.
        char handType = hand.getFirst().charAt(1);

        for (int i = 1; i < hand.size(); i++) {
            //El tipo de carta que vamos a comparar.
            char cardType = hand.get(i).charAt(1);

            //Si el tipo no es igual, mandamos un array vacio para acabar con la funcion.
            if ( cardType != handType ){
                return new ArrayList<String >();
            }
        }

        //Si no se encuentra nada malo regresa la mano inicial.
        return hand;
    }

    /// Checa si la mano tiene un par y una tercia. Si lo tiene, regresa una lista de cinco elementos.
    private static ArrayList<String> hasFullHouse( ArrayList<String> hand ){


        //Checamos si hay una tercia.
        ArrayList<String> threeOfKind = hasThreeOfKind(hand);

        //Si no hay un tercio, esta mal y regresamos un list vacio.
        if (threeOfKind.isEmpty()){
            return new ArrayList<String>();
        }

        hand.removeAll(threeOfKind);

        //Checamos si hay un par.
        ArrayList<String> pair = hasPair(hand);

        //Si esta vacio regresamos respuesta negativa.
        if (pair.isEmpty()) {
            return new ArrayList<String>();
        }

        //Agregamos a la tercia el par para poder mandar las cinco cartas juntas de respuesta.
        threeOfKind.addAll(pair);

        return threeOfKind;
    }
}
