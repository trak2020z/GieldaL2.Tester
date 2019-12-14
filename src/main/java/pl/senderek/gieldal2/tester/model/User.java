package pl.senderek.gieldal2.tester.model;

import java.util.LinkedList;
import java.util.List;
/**
 * Model użytkownika wykorzystywany w logice aplikacji
 */
public class User {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String eMail;
    private Double value;
    private List<Share> shares = new LinkedList<>();
    private List<SellOffer> sellOffers = new LinkedList<>();
    private List<BuyOffer> buyOffers = new LinkedList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public List<Share> getShares() {
        return shares;
    }

    public void setShares(List<Share> shares) {
        this.shares = shares;
    }

    public List<SellOffer> getSellOffers() {
        return sellOffers;
    }

    public void setSellOffers(List<SellOffer> sellOffers) {
        this.sellOffers = sellOffers;
    }

    public List<BuyOffer> getBuyOffers() {
        return buyOffers;
    }

    public void setBuyOffers(List<BuyOffer> buyOffers) {
        this.buyOffers = buyOffers;
    }
}
