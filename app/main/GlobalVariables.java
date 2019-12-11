package main;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import play.Logger;
import play.db.Database;
import com.typesafe.config.Config;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.List;
import java.util.regex.Pattern;

@Singleton
public class GlobalVariables {

    private Database db;
    Connection conn;
    private long lastTimeMillis;
    private JWTVerifier verifier;

    @Inject
    public GlobalVariables(Database db, Config config) throws UnsupportedEncodingException {
        System.out.println("-------------------------------Dream Pass Startingggggggg----------------------------------");
        this.conn = db.getConnection();

        this.lastTimeMillis = System.currentTimeMillis();

        String secret = config.getString("play.http.secret.key");
        Algorithm algorithm = Algorithm.HMAC256(secret);
        this.verifier = JWT.require(algorithm)
                .withIssuer("DreamPass")
                .build();
    }

    public Connection getConnectionDB() {
//        try {
//            if(conn.isClosed()){
//                Logger.info("---------------------------RESET CONNECT DB--------------------------------------");
//                conn = db.getConnection();
//            }
//        } catch (Exception e){
//            Logger.info("------------------------------RESET DB ERROR--------------------------------");
//            Logger.error(e.getMessage());
//        }

        return conn;
    }

    public long generateUniqueId() {
        long time = System.currentTimeMillis();
        if(time <= lastTimeMillis) {time = lastTimeMillis + 1;}
        lastTimeMillis = time;
        return time;
    }

    public JWTVerifier getVerifier(){
        return verifier;
    }
}