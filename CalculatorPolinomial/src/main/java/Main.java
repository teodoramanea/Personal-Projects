import org.example.PolynomData.Polinom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> map1 = new HashMap<>();
       Polinom polinom = new Polinom(map);
       Polinom polinom1 = new Polinom(map1);
       //polinom.addMonom(new Monom(1, 2));
       //polinom.addMonom(new Monom(30, 3));
       //polinom.addMonom(new Monom(25, 1));
     //  polinom.addMonom(new org.example.PolynomData.Monom(-8, 1));
       //polinom.addMonom(new org.example.PolynomData.Monom(1, 0));
       //polinom1.addMonom(new Monom(-29, 3));
       //polinom1.addMonom(new Monom(-49, 1));
       //polinom1.addMonom(new org.example.PolynomData.Monom(1, 2));
       //polinom1.addMonom(new org.example.PolynomData.Monom(2, 1));
       //polinom1.addMonom(new org.example.PolynomData.Monom(-1, 0));
       /* polinom.addMonom(new org.example.PolynomData.Monom(3, 2));
        polinom.addMonom(new org.example.PolynomData.Monom(-1, 1));
        polinom.addMonom(new org.example.PolynomData.Monom(1, 0));
        polinom1.addMonom(new org.example.PolynomData.Monom(1, 1));
        polinom1.addMonom(new org.example.PolynomData.Monom(-2, 0));*/
       /* polinom.addMonom(new org.example.PolynomData.Monom(1, 3));
        polinom.addMonom(new org.example.PolynomData.Monom(-2, 2));
        polinom.addMonom(new org.example.PolynomData.Monom(6, 1));
        polinom.addMonom(new org.example.PolynomData.Monom(-5, 0));*/
       // polinom.addMonom(new Monom(1, 3));
        //polinom.addMonom(new Monom(4, 2));
        //polinom.addMonom(new Monom(5, 0));

       /* polinom.addMonom(new org.example.PolynomData.Monom(1, 2));
        polinom.addMonom(new org.example.PolynomData.Monom(4, 1));
        polinom.addMonom(new org.example.PolynomData.Monom(4, 0));
       // polinom.addMonom(new org.example.PolynomData.Monom(-6, 0));
        polinom1.addMonom(new org.example.PolynomData.Monom(1, 1));
        polinom1.addMonom(new org.example.PolynomData.Monom(2, 0));*/
      //  polinom1.addMonom(new org.example.PolynomData.Monom(-3, 0));
       /* for(Map.Entry<Integer, Integer> entry: polinom1.getPoli().entrySet())
        {
           System.out.println("exponent=" + entry.getKey() + "; " +
                    "coeficient=" + entry.getValue());
          // System.out.println(polinom.getPoli().get(entry.getKey()).intValue());

        }*/
        Map<Integer, Integer> map2 = new HashMap<>();
        Polinom polinom2;
        //polinom2 = org.example.PolynomData.Polinom.add(polinom, polinom1);
       // polinom2 = org.example.PolynomData.Polinom.sub(polinom, polinom1);
       // polinom2 = org.example.PolynomData.Polinom.mul(polinom, polinom1);
        //polinom2 = org.example.PolynomData.Polinom.deriv(polinom);
       // polinom2 = org.example.PolynomData.Polinom.integral(polinom);
        List<Integer> polinomul = new ArrayList<>(polinom1.getPoli().keySet());
      /*  for (Integer key : polinomul) {
            Integer value = polinom1.getPoli().get(key);
            System.out.println(key + " => " + value);
        }*/
        polinom2 = Polinom.add(polinom, polinom1);
        System.out.println("catul");
        for(Map.Entry<Integer, Integer> entry: polinom2.getPoli().entrySet())
        {

            System.out.println("exponent=" + entry.getKey() + "; " +
                    "coeficient=" + entry.getValue());

        }


    }
}