package pl.senderek.gieldal2.tester.controller;

import pl.senderek.gieldal2.tester.model.TestParams;
import pl.senderek.gieldal2.tester.model.User;
import pl.senderek.gieldal2.tester.service.external.*;

import java.util.Date;

public class ClientRandom extends Client {

    public ClientRandom(UserService userService, ShareService shareService, StockService stockService, BuyOfferService buyOfferService,
                        SellOfferService sellOfferService, User user, Long clientId, Date testStartTime, TestParams testParams) {
        super(userService, shareService, stockService, buyOfferService, sellOfferService, user, clientId, testStartTime, testParams);
    }

    @Override
    public void run() {
        performLogIn();

        while (actionNo < testParams.getMaxNoOfActions()) {
            try {
                int r = random.nextInt(100);
                if (r < 35)
                    performBuy();
                else if (r < 70)
                    performSell();
                else if (r < 74)
                    performBuyOffersCheck();
                else if (r < 78)
                    performSellOffersCheck();
                else if (r < 82)
                    performSharesCheck();
                else if (r < 86)
                    performClientSharesCheck();
                else if (r < 90)
                    performStocksCheck();
                else if (r < 97)
                    performProfileCheck();
                else
                    performProfileUpdate();
            } catch (Exception e) {
                System.err.println(context);
                e.printStackTrace();
            } finally {
                actionNo++;
            }
        }
    }

}
