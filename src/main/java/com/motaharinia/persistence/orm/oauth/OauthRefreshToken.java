/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motaharinia.persistence.orm.oauth;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author Dev3
 */
@Entity
@Table(name = "oauth_refresh_token")
public class OauthRefreshToken implements Serializable {

    @Id
    @Column(name = "token_id")
    private String token_id;

    @Lob
    @Column(name = "token")
    private Byte[] token;

    @Lob
    @Column(name = "authentication")
    private Byte[] authentication;

    //getter-setter:
    public String getToken_id() {
        return token_id;
    }

    public void setToken_id(String token_id) {
        this.token_id = token_id;
    }

    public Byte[] getToken() {
        return token;
    }

    public void setToken(Byte[] token) {
        this.token = token;
    }

    public Byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Byte[] authentication) {
        this.authentication = authentication;
    }

}
