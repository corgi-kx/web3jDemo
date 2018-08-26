package com.haimian.bl.bean;

public class UserBean {
    private int user_index;


    private String id_card;
    private String password;
    private int states;
    private String eth_address;
    private String eth_private_key;
    private String eth_public_key;
    private String eth_key_store;
    private int score;

    public int getUser_index() {
        return user_index;
    }

    public void setUser_index(int user_index) {
        this.user_index = user_index;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStates() {
        return states;
    }

    public void setStates(int states) {
        this.states = states;
    }

    public String getEth_address() {
        return eth_address;
    }

    public void setEth_address(String eth_address) {
        this.eth_address = eth_address;
    }

    public String getEth_private_key() {
        return eth_private_key;
    }

    public void setEth_private_key(String eth_private_key) {
        this.eth_private_key = eth_private_key;
    }

    public String getEth_public_key() {
        return eth_public_key;
    }

    public void setEth_public_key(String eth_public_key) {
        this.eth_public_key = eth_public_key;
    }

    public String getEth_key_store() {
        return eth_key_store;
    }

    public void setEth_key_store(String eth_key_store) {
        this.eth_key_store = eth_key_store;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
