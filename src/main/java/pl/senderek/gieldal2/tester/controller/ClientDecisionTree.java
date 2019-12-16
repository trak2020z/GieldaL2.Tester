package pl.senderek.gieldal2.tester.controller;

import pl.senderek.gieldal2.tester.model.TestParams;
import pl.senderek.gieldal2.tester.model.User;
import pl.senderek.gieldal2.tester.service.external.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Klasa opisująca klienta, którego decyzje wyznaczane są na podstawie drzewa decyzyjnego.
 */
public class ClientDecisionTree extends Client {

    /**
     * Dostępne dla klienta akcje
     */
    private enum Action {
        LOGIN, LOGOUT, BUY, SELL, BUY_OFFERS_CHECK, SELL_OFFERS_CHECK, SHARES_CHECK, CLIENT_SHARES_CHECK, STOCKS_CHECK, USERS_CHECK,
        PROFILE_CHECK, PROFILE_UPDATE
    }

    public ClientDecisionTree(UserService userService, ShareService shareService, StockService stockService, BuyOfferService buyOfferService,
                              SellOfferService sellOfferService, User user, Long clientId, Date testStartTime, TestParams testParams) {
        super(userService, shareService, stockService, buyOfferService, sellOfferService, user, clientId, testStartTime, testParams);
    }

    /**
     * Logowanie klienta oraz wykonywanie losowych akcji, dostępnych po poprzedniej, aż do wylogowania lub wykonania ustalonej w konfiguracji liczby akcji
     */
    @Override
    public void run() {
        performLogIn();
        Action lastAction = Action.LOGIN;

        while (actionNo < testParams.getMaxNoOfActions()) {
            try {
                List<Action> nextPossibleActions = getNextPossibleActions(lastAction);
                int r = random.nextInt(nextPossibleActions.size());
                lastAction = nextPossibleActions.get(r);
                performAction(lastAction);
            } catch (Exception e) {
                System.err.println(context);
                e.printStackTrace();
            } finally {
                actionNo++;
            }
        }
    }

    /**
     * Metoda zwracająca liste dostępnych akcji typu {@link Action}, wyznaczaną na podstawie wcześcniejszej akcji
     * @param lastAction Wcześniejsza akcja wykonana przez klienta
     * @return Lista aktualnie dostępnych dla klienta akcji
     */
    private List<Action> getNextPossibleActions(Action lastAction) {
        List<Action> nextPossibleActions = new ArrayList<>();
        nextPossibleActions.add(Action.BUY_OFFERS_CHECK);
        nextPossibleActions.add(Action.SELL_OFFERS_CHECK);
        nextPossibleActions.add(Action.SHARES_CHECK);
        nextPossibleActions.add(Action.CLIENT_SHARES_CHECK);
        nextPossibleActions.add(Action.STOCKS_CHECK);
        nextPossibleActions.add(Action.USERS_CHECK);
        nextPossibleActions.add(Action.PROFILE_CHECK);
        nextPossibleActions.add(Action.LOGOUT);

        switch (lastAction) {
            case BUY_OFFERS_CHECK:
            case STOCKS_CHECK:
                nextPossibleActions.add(Action.BUY);
                break;
            case SELL_OFFERS_CHECK:
            case SHARES_CHECK:
            case CLIENT_SHARES_CHECK:
                nextPossibleActions.add(Action.SELL);
                break;
            case PROFILE_CHECK:
                nextPossibleActions.add(Action.PROFILE_UPDATE);
                break;
        }

        return nextPossibleActions;
    }

    /**
     * Wykonanie podanej do metody akcji
     * @param action Akcja która zostanie wykonana po wywołaniu funkcji
     * @throws Exception Wyjątek przechwycony przy błędzie serwera
     */
    private void performAction(Action action) throws Exception {
        switch (action) {
            case BUY:
                performBuy();
                break;
            case SELL:
                performSell();
                break;
            case BUY_OFFERS_CHECK:
                performBuyOffersCheck();
                break;
            case SELL_OFFERS_CHECK:
                performSellOffersCheck();
                break;
            case SHARES_CHECK:
                performSharesCheck();
                break;
            case CLIENT_SHARES_CHECK:
                performClientSharesCheck();
                break;
            case STOCKS_CHECK:
                performStocksCheck();
                break;
            case USERS_CHECK:
                performUsersCheck();
                break;
            case PROFILE_CHECK:
                performProfileCheck();
                break;
            case PROFILE_UPDATE:
                performProfileUpdate();
                break;
            case LOGOUT:
                actionNo = testParams.getMaxNoOfActions();
                System.out.println("User " + context.getClientId() + " logout");
                break;
        }
    }

}
