package com.acme.mytrader.price;

public interface PriceListener {

    /*
    This method listen to price provider Ex SCB and get the
    updated price of a security.
    */
    void priceUpdate(String security, double price);
}
