package pl.senderek.gieldal2.tester.model;

import java.util.List;

public class User {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String mail;
    private Double value;
    private List<Share> shares;
    private List<SellOffer> sellOffers;
    private List<BuyOffer> buyOffers;

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
