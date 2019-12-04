package pl.senderek.gieldal2.tester.controller;

import pl.senderek.gieldal2.tester.model.*;
import pl.senderek.gieldal2.tester.service.external.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public abstract class Client extends Thread {

    private final UserService userService;
    private final ShareService shareService;
    private final StockService stockService;
    private final BuyOfferService buyOfferService;
    private final SellOfferService sellOfferService;
    final TestParams testParams;
    final Random random;

    protected TestContext context;
    private User user;
    private String token;
    int actionNo;

    Client(UserService userService, ShareService shareService, StockService stockService, BuyOfferService buyOfferService,
                  SellOfferService sellOfferService, User user, Long clientId, Date testStartTime, TestParams testParams) {
        context = new TestContext(clientId, testStartTime);
        this.user = user;
        this.userService = userService;
        this.shareService = shareService;
        this.stockService = stockService;
        this.buyOfferService = buyOfferService;
        this.sellOfferService = sellOfferService;
        this.testParams = testParams;
        random = new Random();
    }

    @Override
    public void start() {
        actionNo = 0;
        super.start();
    }

    void performLogIn() {
        token = userService.authenticateUser(user);
        String password = user.getPassword();
        user = userService.getUserContext(token);
        user.setPassword(password);
    }

    void performBuy() throws Exception {
        List<Stock> stocks = stockService.getAllStocks(context, token);
        if (stocks != null && !stocks.isEmpty()) {
            Stock stock = stocks.get(random.nextInt(stocks.size()));

            BuyOffer buyOffer = new BuyOffer();
            buyOffer.setBuyer(user);
            buyOffer.setStock(stock);
            buyOffer.setAmount(random.nextInt((int) (user.getValue() / stock.getCurrentPrice())) + 1);
            buyOffer.setPrice(stock.getCurrentPrice() - testParams.getOfferFirstPriceDiff() * stock.getCurrentPrice());

            performBuy(buyOffer, 0);
        }
    }

    private void performBuy(BuyOffer buyOffer, int changesNumber) throws Exception {
        buyOffer.setDate(LocalDateTime.now());
        buyOfferService.createBuyOffer(context, buyOffer, token);

        if (changesNumber <= testParams.getOfferMaxNumOfPriceChanges()) {
            Thread.sleep(testParams.getOfferSleepTime());

            List<BuyOffer> buyOffers = buyOfferService.getAllBuyOffers(context, token);
            if (buyOffers != null && !buyOffers.isEmpty()) {
                BuyOffer last = buyOffers.get(buyOffers.size() - 1);
                if (last.getDate().isEqual(buyOffer.getDate().truncatedTo(ChronoUnit.MILLIS))) {
                    buyOfferService.deleteBuyOffer(context, last.getId(), token);
                    buyOffer.setAmount(last.getAmount());
                    double newPrice = buyOffer.getPrice() + buyOffer.getPrice() * testParams.getBuyOfferPriceDiff();
                    if (user.getValue() > newPrice * buyOffer.getAmount())
                        buyOffer.setPrice(newPrice);
                    performBuy(buyOffer, changesNumber + 1);
                }
            }
        }
    }

    void performSell() throws Exception {
        if (user.getShares() != null && !user.getShares().isEmpty()) {
            Share share = user.getShares().get(random.nextInt(user.getShares().size()));

            SellOffer sellOffer = new SellOffer();
            sellOffer.setSeller(user);
            sellOffer.setShare(share);
            sellOffer.setAmount(random.nextInt((share.getAmount().intValue())) + 1);
            sellOffer.setPrice(share.getStock().getCurrentPrice() + testParams.getOfferFirstPriceDiff() * share.getStock().getCurrentPrice());

            performSell(sellOffer, 0);
        }
    }

    private void performSell(SellOffer sellOffer, int changesNumber) throws Exception {
        sellOffer.setDate(LocalDateTime.now());
        sellOfferService.createSellOffer(context, sellOffer, token);

        if (changesNumber <= testParams.getOfferMaxNumOfPriceChanges()) {
            Thread.sleep(testParams.getOfferSleepTime());

            List<SellOffer> sellOffers = sellOfferService.getAllSellOffers(context, token);
            if (sellOffers != null && !sellOffers.isEmpty()) {
                SellOffer last = sellOffers.get(sellOffers.size() - 1);
                if (last.getDate().isEqual(sellOffer.getDate().truncatedTo(ChronoUnit.MILLIS))) {
                    sellOfferService.deleteSellOffer(context, last.getId(), token);
                    sellOffer.setAmount(last.getAmount());
                    sellOffer.setPrice(sellOffer.getPrice() - sellOffer.getPrice() * testParams.getSellOfferPriceDiff());
                    performSell(sellOffer, changesNumber + 1);
                }
            }
        }
    }

    void performProfileCheck() {
        Optional<User> user = userService.getUser(context, this.user.getId(), token);
    }

    void performProfileUpdate() {
        userService.modifyUser(context, user, token);
    }

    void performBuyOffersCheck() {
        buyOfferService.getAllBuyOffers(context, token);
    }

    void performSellOffersCheck() {
        sellOfferService.getAllSellOffers(context, token);
    }

    void performSharesCheck() {
        shareService.getAllShares(context, token);
    }

    void performClientSharesCheck() {
        shareService.getUserShares(context, user, token);
    }

    void performStocksCheck() {
        stockService.getAllStocks(context, token);
    }

}
