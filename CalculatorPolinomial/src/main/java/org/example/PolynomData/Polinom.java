package org.example.PolynomData;

import java.util.*;

public  class Polinom {
    private Map<Integer, Integer> poli;
    private Boolean integrare = false;
    public Polinom(Map<Integer, Integer> poli) {
        this.poli = poli;
    }

    public Map<Integer, Integer> getPoli() {
        return poli;
    }

    public void setPoli(Map<Integer, Integer> poli) {
        this.poli = poli;
    }

    ///maparea coeficientului in functie de exp
    public void addMonom(Monom monom)
    {
        if(poli.containsKey(monom.getExponent()) == false)
            poli.put(monom.getExponent(), monom.getCoeficient());
    }

   public static Polinom add(Polinom polinom1, Polinom polinom2) {
       Map<Integer, Integer> map = new HashMap<>();
         Polinom polinom = new Polinom(map);
        for (Map.Entry<Integer, Integer> entry : polinom1.poli.entrySet()) {
            int coef1 = entry.getValue();
            int coef2 = 0;
            if(polinom2.poli.containsKey(entry.getKey()))
                 coef2 = polinom2.poli.get(entry.getKey());
            int sum = coef1 + coef2;
            if(sum != 0)
                polinom.poli.put(entry.getKey(), sum);
        }
        for (Map.Entry<Integer, Integer> entry : polinom2.poli.entrySet()) {
            if (!polinom1.poli.containsKey(entry.getKey())) {
                if(entry.getValue() != 0)
                  polinom.poli.put(entry.getKey(), entry.getValue());
            }
        }
        System.out.println(polinom.poli);
        return polinom;
    }



    public static Polinom sub(Polinom polinom1, Polinom polinom2)
    {
        Map<Integer, Integer> map = new HashMap<>();
        Polinom polinom = new Polinom(map);
        for (Map.Entry<Integer, Integer> entry : polinom2.poli.entrySet())
        {
            Integer key = entry.getKey();
            if(polinom1.poli.containsKey(key))
            {
                int result = polinom1.poli.get(key) - entry.getValue();
                if(result != 0)
                    polinom.poli.put(key, result);
            }
            else if(!polinom1.poli.containsKey(key))
                polinom.poli.put(key, -entry.getValue());
        }
        for (Map.Entry<Integer, Integer> entry : polinom1.poli.entrySet()) {
            if(!polinom2.poli.containsKey(entry.getKey()))
                if(entry.getValue() != 0)
                    polinom.poli.put(entry.getKey(), entry.getValue());
        }
        return polinom;
    }

    public static Polinom mul(Polinom polinom1, Polinom polinom2)
    {
        Map<Integer, Integer> map = new HashMap<>();
        Polinom polinom = new Polinom(map);
        for (Map.Entry<Integer, Integer> entry : polinom1.poli.entrySet())
        {
            for (Map.Entry<Integer, Integer> entry1 : polinom2.poli.entrySet())
            {
                int coef1 = entry.getValue();
                int coef2 = entry1.getValue();
                int exp1 = entry.getKey();
                int exp2 = entry1.getKey();
                int key = exp1+exp2;
              //  System.out.println(key);
                if(!polinom.poli.containsKey(key))
                    polinom.poli.put(key, coef1*coef2);
                else polinom.poli.put(key, polinom.poli.get(key) + coef1 * coef2);
            }
        }
        return polinom;
    }
    public static Polinom deriv(Polinom polinom1)
    {
        Map<Integer, Integer> map = new HashMap<>();
        Polinom polinom = new Polinom(map);
        for (Map.Entry<Integer, Integer> entry : polinom1.poli.entrySet())
        {
            int exp = entry.getKey();
            int coef = entry.getValue();
            if(coef !=0 && exp != 0)
            {
                polinom.poli.put(entry.getKey() -1, exp * coef);
            }
        }
        return polinom;
    }
    public static Polinom integral(Polinom polinom1)
    {
        Map<Integer, Integer> map = new HashMap<>();
        Polinom polinom = new Polinom(map);
        polinom.integrare = true;
        for (Map.Entry<Integer, Integer> entry : polinom1.poli.entrySet())
        {
            int exp = entry.getKey();
            int coef = entry.getValue();
            polinom.poli.put(exp + 1, coef);
        }
        return polinom;
    }
    public static Polinom divide(Polinom polinom1, Polinom polinom2)
    {
        Map<Integer, Integer> map = new HashMap<>();
        Polinom polinom = new Polinom(map);
        ///de rezolvat aici sa nu se mai repete codul
        List<Integer> polinomul = new ArrayList<>(polinom1.poli.keySet());
        Collections.sort(polinomul, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        List<Integer> polinomul1 = new ArrayList<>(polinom2.poli.keySet());
        Collections.sort(polinomul1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        Integer aux = polinomul1.get(0);
            while(polinomul.size() != 0 && polinomul.get(0) >= aux) {
                Integer key = polinomul.get(0);
                int exp = key - aux;
                int coef = polinom1.poli.get(key).intValue() / polinom2.poli.get(aux).intValue();
                polinom.poli.put(exp, coef);
                // System.out.println(coef);

                for (Map.Entry<Integer, Integer> entry : polinom2.poli.entrySet()) {
                    int exp1 = exp + entry.getKey();
                    int coef2 = -(coef * entry.getValue());

                    // System.out.println(coef2);
                    for (int j = 0; j < polinomul.size(); j++) {
                        if (polinomul.get(j).equals(exp1)) {
                            if (polinom1.poli.get(polinomul.get(j)).intValue() == -(coef2)) {
                                polinom1.poli.remove(exp1);
                                ///eliminam si din lista ordonata de chei
                                for (int i = 0; i < polinomul.size(); i++) {
                                    if (polinomul.get(i) == exp1) {
                                        polinomul.remove(i);
                                        break;
                                    }
                                }
                            } else if (polinom1.poli.get(polinomul.get(j)).intValue() != -(coef2)) {
                                polinom1.poli.put(polinomul.get(j), polinom1.poli.get(polinomul.get(j)).intValue() + coef2);
                            }
                        }
                    }
                }

                // System.out.println(polinomul.get(0));
            }
        System.out.println("restul");
        for (Map.Entry<Integer, Integer> entry1 : polinom1.poli.entrySet())
        {
            System.out.println("exponent=" + entry1.getKey() + "; " +
                    "coeficient=" + entry1.getValue());
        }

        return polinom;
    }
    
    @Override
    public String toString() {
        String polinom = new String();
        for (Map.Entry<Integer, Integer> entry : poli.entrySet()) {
            int coef = entry.getValue();
            int exp = entry.getKey();
            if (coef != 0 && exp != 0 && exp != 1) {
                if (coef < 0) {
                    if(integrare.equals(true))
                    {
                        if(coef == -1)
                            polinom = polinom +  '-' + '1' + '/' + exp + '*' +  'x' +  '^' + exp;
                       else polinom = polinom + " " + '1' + '/' + exp + '*' + '(' + coef + 'x' + ')' + '^' + exp;
                    }
                    else
                    {
                        if(coef == -1)
                            polinom = polinom  + '-' + 'x'  + '^' + exp;
                       else polinom = polinom +  coef + 'x' + '^' + exp;
                    }
                }
                else
                {
                    if(integrare.equals(true))
                    {
                        if(coef == 1)
                            polinom = polinom + '+' + '1' + '/' + exp  + '*'  + 'x' + '^' + exp;
                        else  polinom = polinom + '+' + '1' + '/' + exp  + '*' + coef + 'x' + '^' + exp;
                    }

                   else
                    {
                        if(coef == 1)
                            polinom = polinom + '+' + 'x' + '^' + exp;
                        else polinom = polinom + '+' + coef + 'x' + '^' + exp;
                    }
                }
            }
            else if(coef != 0 && exp == 1)
            {
                if (coef < 0) {
                        if(coef == -1)
                            polinom = polinom +  '-' + 'x';
                        else polinom = polinom +  coef + 'x';
                }
                else
                {
                        if(coef == 1)
                            polinom = polinom + '+' + 'x';
                       else polinom = polinom + '+' + coef + 'x';
                }
            }
            else if(coef != 0 && exp == 0)
            {
                if (coef < 0) {
                    polinom = polinom +  coef;
                }
                else
                {
                    polinom = polinom + '+' + coef;
                }
            }
            else if(coef == 0)
                polinom = polinom + '0';
        }
        return polinom;
    }


}

