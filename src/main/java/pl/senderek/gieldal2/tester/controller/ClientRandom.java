package pl.senderek.gieldal2.tester.controller;

import pl.senderek.gieldal2.tester.model.TestParams;
import pl.senderek.gieldal2.tester.model.User;
import pl.senderek.gieldal2.tester.service.external.*;

import java.util.Date;
import java.util.Iterator;

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
                Iterator<Integer> rci = testParams.getRandomChances().iterator();
                int r = random.nextInt(100);
                if (r < rci.next())
                    performBuy();
                else if (r < rci.next())
                    performSell();
                else if (r < rci.next())
                    performBuyOffersCheck();
                else if (r < rci.next())
                    performSellOffersCheck();
                else if (r < rci.next())
                    performSharesCheck();
                else if (r < rci.next())
                    performClientSharesCheck();
                else if (r < rci.next())
                    performStocksCheck();
                else if (r < rci.next())
                    performUsersCheck();
                else if (r < rci.next())
                    performProfileCheck();
                else if (r < rci.next())
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
