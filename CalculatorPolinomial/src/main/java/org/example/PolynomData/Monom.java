package org.example.PolynomData;

public class Monom {
    private Integer coeficient;
    private Integer exponent;

    public Monom(Integer coeficient, Integer exponent) {
        this.coeficient = coeficient;
        this.exponent = exponent;
    }

    public Integer getCoeficient() {
        return coeficient;
    }

    public void setCoeficient(Integer coeficient) {
        this.coeficient = coeficient;
    }

    public Integer getExponent() {
        return exponent;
    }

    public void setExponent(Integer exponent) {
        this.exponent = exponent;
    }

}
