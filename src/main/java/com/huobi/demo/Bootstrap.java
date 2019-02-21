package com.huobi.demo;

import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class Bootstrap {

  public static void main(String[] args) {
    SpringApplication.run(Bootstrap.class, args);
  }

  @Value("${uri.protocol:wss://}")
  String protocol;

  //huobi cloud don't add '/api' eg. uri.host:www.huobi.com.ru
  @Value("${uri.host:api.huobi.pro}")
  String host;

  @Value("${uri.port:443}")
  String port;

  @Value("${uri.ao.path:/ws/v1}")
  String aO;

  //huobi cloud uri.market.path is /api/ws
  @Value("${uri.market.path:/ws}")
  String market;

  @Value("${accessKey}")
  String accessKey;

  @Value("${secretKey}")
  String secretKey;

  @Autowired
  Client client;



  @PostConstruct
  private void init() {
    try {
      URI uri = new URI(protocol + host + ":" + port + aO);
      WebSocketClient ws = new WebSocketAccountsAndOrders(uri, accessKey, secretKey);

      client.connect(ws);
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }


//    账号及订单websocket

  }
}
