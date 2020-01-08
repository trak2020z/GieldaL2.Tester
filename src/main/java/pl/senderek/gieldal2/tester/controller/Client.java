package pl.senderek.gieldal2.tester.controller;

import pl.senderek.gieldal2.tester.exception.EmptyUserContextException;
import pl.senderek.gieldal2.tester.model.*;
import pl.senderek.gieldal2.tester.service.external.*;
import pl.senderek.gieldal2.tester.service.external.impl.UserServiceImpl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Opisuje klienta giełdy, z metodami wykonującymi dostępne mu akcje. Z klasy tej dziedziczą klasy {@link ClientDecisionTree} oraz {@link ClientRandom}
 * dodając specyficzne dla nich pola i metody
 */
public abstract class Client extends Thread {
    /**
     * Obiekt serwisu działający na użytkownikach
     */
    private final UserService userService;
    /**
     * Obiekt serwisu działający udziałach
     */
    private final ShareService shareService;
    /**
     * Obiekt serwisu działający na akcjach
     */
    private final StockService stockService;
    /**
     * Obiekt serwisu działający na ofertach kupna
     */
    private final BuyOfferService buyOfferService;
    /**
     * Obiekt serwisu działający ofertach sprzedaży
     */
    private final SellOfferService sellOfferService;
    /**
     *
     */
    final TestParams testParams;
    final Random random;

    protected TestContext context;
    /**
     * Obiekt użytkownika przypisany do klienta
     */
    private User user;
    /**
     * Token klienta
     */
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

    /**
     * Metoda uruchamiająca wątek
     */
    @Override
    public void start() {
        actionNo = 0;
        super.start();
    }

    /**
     * Metoda uzyskująca token użytkownika, oraz pobierająca z API wszytkie informacje z nim związane, przez wywołanie {@link UserServiceImpl#getUserContext(TestContext, String)}
     */
    void performLogIn() {
        token = userService.authenticateUser(user);
        String password = user.getPassword();
        user = userService.getUserContext(context, token).orElseThrow(EmptyUserContextException::new);
        user.setPassword(password);
    }

    /**
     * Metoda ustala właściwości oferty kupna, tak aby było na nią stać użytkownika. Cena jednej akcji obliczana jest na podstawie ceny pobranej z API zmniejszonej o ustalony procent.
     * Kupno akcji odbywa się przez wywołanie funkcji {@link #performBuy(BuyOffer, int)}
     *
     * @throws Exception Wyjątek zgłaszany przy przerwaniu wątku w trakcie wywoływania funkcji {@code sleep}
     */
    void performBuy() throws Exception {
        List<Stock> stocks = stockService.getAllStocks(context, token);
        if (stocks != null && !stocks.isEmpty() && user.getValue() > 0) {
            Stock stock = stocks.get(random.nextInt(stocks.size()));

            BuyOffer buyOffer = new BuyOffer();
            buyOffer.setBuyer(user);
            buyOffer.setStock(stock);
            buyOffer.setAmount(random.nextInt((int) (user.getValue() / stock.getCurrentPrice())) + 1);
            buyOffer.setPrice(stock.getCurrentPrice() - testParams.getOfferFirstPriceDiff() * stock.getCurrentPrice());
            performBuy(buyOffer, 0);
        }
    }

    /**
     * Metoda tworzy oferte kupna podaną do funkcji.
     *
     * @param buyOffer      Utworzona w {@link #performBuy()} oferta kupna
     * @param changesNumber Liczba zmian ceny kupna. Zwiększenie ceny następuje jeśli nie udało się kupić akcji po poprzedniej cenie
     * @throws Exception Wyjątek zgłaszany przy przerwaniu wątku w trakcie wywoływania funkcji {@code sleep}
     */
    private void performBuy(BuyOffer buyOffer, int changesNumber) throws Exception {
        buyOffer.setDate(LocalDateTime.now());
        buyOfferService.createBuyOffer(context, buyOffer, token);
        if (changesNumber <= testParams.getOfferMaxNumOfPriceChanges()) {
            Thread.sleep(testParams.getOfferSleepTime());

            List<BuyOffer> buyOffers = buyOfferService.getAllBuyOffers(context, token);
            if (buyOffers != null && !buyOffers.isEmpty()) {
                Optional<BuyOffer> lastOpt = buyOffers.stream().filter(x -> x.getDate().equals(buyOffer.getDate().truncatedTo(ChronoUnit.MILLIS))
                        && (x.getBuyer().getId().equals(buyOffer.getBuyer().getId()))).findFirst();
                if (lastOpt.isPresent()) {
                    BuyOffer last = lastOpt.get();
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

    /**
     * Metoda ustala właściwości oferty sprzedaży. Ustalana jest ilość sprzedawanych udziałów, oraz cena na podstawie danych z API i konfiguracji
     * Sprzedaż akcji odbywa się przez wywołanie funkcji {@link #performSell(SellOffer, int)}
     *
     * @throws Exception Wyjątek zgłaszany przy przerwaniu wątku w trakcie wywoływania funkcji {@code sleep}
     */
    void performSell() throws Exception {
        if (user.getShares() != null && !user.getShares().isEmpty()) {
            Share share = user.getShares().get(random.nextInt(user.getShares().size()));
            Optional<Stock> stock = stockService.getStock(context, share.getStock().getId(), token);

            if (share.getAmount() > 0 && stock.isPresent()) {
                SellOffer sellOffer = new SellOffer();
                sellOffer.setSeller(user);
                sellOffer.setShare(share);
                sellOffer.setAmount(random.nextInt((share.getAmount().intValue())) + 1);
                sellOffer.setPrice(stock.get().getCurrentPrice() + testParams.getOfferFirstPriceDiff() * stock.get().getCurrentPrice());

                performSell(sellOffer, 0);
            }
        }
    }

    /**
     * Metoda tworzy oferte sprzedaży podaną do funkcji.
     *
     * @param sellOffer     Utworzona w {@link #performSell()} ()} oferta sprzedaży
     * @param changesNumber Liczba zmian ceny sprzedaży. Zmniejszenie ceny następuje jeśli nie udało się sprzedać akcji po poprzedniej cenie
     * @throws Exception Wyjątek zgłaszany przy przerwaniu wątku w trakcie wywoływania funkcji {@code sleep}
     */
    private void performSell(SellOffer sellOffer, int changesNumber) throws Exception {
        sellOffer.setDate(LocalDateTime.now());
        sellOfferService.createSellOffer(context, sellOffer, token);

        if (changesNumber <= testParams.getOfferMaxNumOfPriceChanges()) {
            Thread.sleep(testParams.getOfferSleepTime());

            List<SellOffer> sellOffers = sellOfferService.getAllSellOffers(context, token);
            if (sellOffers != null && !sellOffers.isEmpty()) {
                Optional<SellOffer> lastOpt = sellOffers.stream().filter(x -> x.getDate().equals(sellOffer.getDate().truncatedTo(ChronoUnit.MILLIS)) &&
                        x.getSeller().equals(sellOffer.getSeller())).findFirst();
                if (lastOpt.isPresent()) {
                    SellOffer last = lastOpt.get();
                    sellOfferService.deleteSellOffer(context, last.getId(), token);
                    sellOffer.setAmount(last.getAmount());
                    sellOffer.setPrice(sellOffer.getPrice() - sellOffer.getPrice() * testParams.getSellOfferPriceDiff());
                    performSell(sellOffer, changesNumber + 1);
                }
            }
        }
    }

    /**
     * Zapisanie informacji o użytkowniku w polu {@link #user}
     */
    void performProfileCheck() {
        userService.getUser(context, this.user.getId(), token).ifPresent(value -> this.user = value);
    }

    /**
     * Modyfikacja użytkownika
     */
    void performProfileUpdate() {
        userService.modifyUser(context, user, token);
    }

    /**
     * Sprawdzenie ofert kupna
     */
    void performBuyOffersCheck() {
        buyOfferService.getAllBuyOffers(context, token);
    }

    /**
     * Sprawdzenie ofert sprzedaży
     */
    void performSellOffersCheck() {
        sellOfferService.getAllSellOffers(context, token);
    }

    /**
     * Sprawdzenie udziałów
     */
    void performSharesCheck() {
        shareService.getAllShares(context, token);
    }

    /**
     * Sprawdzenie udziałów użytkownika
     */
    void performClientSharesCheck() {
        shareService.getUserShares(context, user, token);
    }

    /**
     * Sprawdzenie akcji
     */
    void performStocksCheck() {
        stockService.getAllStocks(context, token);
    }

    /**
     * Sprawdzenie użytkowników
     */
    void performUsersCheck() {
        userService.getAllUsers(context, token);
    }

}
