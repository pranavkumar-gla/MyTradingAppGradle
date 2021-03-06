package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;

public class PriceListenerImpl implements PriceListener {

    private final String BUY="BUY";
    private final String SELL="SELL";

    private ExecutionService executionService;
    private com.acme.mytrader.price.PriceSource priceSource;

    private double pricePoint;
    private int lotSize;
    private String securityName;
    private String tradeType;

    public PriceListenerImpl(ExecutionService executionService,
                             PriceSource priceSource,
                             double pricePoint,
                             int lotSize,
                             String securityName,
                             String tradeType
                                ) {
        this.executionService = executionService;
        this.priceSource = priceSource;
        this.pricePoint = pricePoint; /*price point set by system*/
        this.lotSize = lotSize;
        this.securityName = securityName;
        this.tradeType = tradeType;
    }

    @Override
    public void priceUpdate(String security, double price) {

        if ( BUY.equalsIgnoreCase(tradeType) && security.equals(securityName) && price <= this.pricePoint ) {
            executionService.buy(securityName, price, lotSize);
            priceSource.removePriceListener(this);

        }else if ( SELL.equalsIgnoreCase(tradeType) && security.equals(securityName) && price >= this.pricePoint ) {
            executionService.sell(securityName, price, lotSize);
            priceSource.removePriceListener(this);
        }
    }
}
