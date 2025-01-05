
package com.dev.UserService.Service;

import com.dev.UserService.Models.Session;
import com.dev.UserService.Models.SessionStatus;
import com.dev.UserService.Models.User;
import com.dev.UserService.Repository.SessionRepository;
import com.dev.UserService.Repository.UserRepository;
import com.dev.UserService.config.KafkaProducerConfig;
import com.dev.UserService.dto.SendEmailDto;
import com.dev.UserService.dto.Userdto;
import com.dev.UserService.Exception.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.*;

@Service
public class AuthService {

    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private KafkaProducerConfig kafkaProducerConfig;
    private ObjectMapper objectMapper;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder, KafkaProducerConfig kafkaProducerConfig, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.kafkaProducerConfig = kafkaProducerConfig;
        this.objectMapper = objectMapper;
    }


    public ResponseEntity<Userdto> login(String email, String password) {
        Optional<User> useroptional = userRepository.findByEmail(email);
        if (useroptional.isEmpty()) {
            throw new UserNotfoundException("User Not found");
        }
        User user = useroptional.get();
        if (bCryptPasswordEncoder.encode(password).matches(user.getPassword())) {
            throw new InvalidCredentialException("Invalid Credentails");
        }
        Userdto userdto = Userdto.from(user);
        //String token = RandomStringUtils.randomAlphanumeric(30);
        //token generation
        MacAlgorithm alg = Jwts.SIG.HS256; // HS256 algo added for JWT
        SecretKey key = alg.key().build(); // generating the secret key

        //start adding the claims
        Map<String, Object> jsonForJWT = new HashMap<>();
        jsonForJWT.put("userId", user.getId());
        jsonForJWT.put("roles", user.getRoles());
        jsonForJWT.put("createdAt", new Date());
        jsonForJWT.put("expiryAt", new Date(LocalDate.now().plusDays(3).toEpochDay()));

        String token = Jwts.builder()
                .claims(jsonForJWT)  //added the claims
                .signWith(key, alg) // added the algo and key
                .compact(); //building the token
        Session session = new Session();
        session.setLoginAt(new Date());
        session.setToken(token);
        session.setUser(user);
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setExpiringAt(new Date());
        sessionRepository.save(session);
        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE,token);
        ResponseEntity<Userdto> responseEntity = new ResponseEntity<>(userdto, headers, HttpStatus.OK);
        return responseEntity;
    }

    public ResponseEntity<Void> logout(Long Id, String token) {
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, Id);
        if (sessionOptional.isEmpty()){
            throw new InvalidTokenException();
        }
        Session session = sessionOptional.get();
        session.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(session);
        return ResponseEntity.ok().build();
    }

    public Userdto signup(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
//        try{
//            SendEmailDto sendEmailDto = new SendEmailDto();
//            sendEmailDto.setFrom("selvarajbalaraman.1963@gmail.com");
//            sendEmailDto.setTo(email);
//            sendEmailDto.setBody("Successfully Signed in");
//            sendEmailDto.setSubject("Sign in Status");
//            kafkaProducerConfig.sendMessage("sign_up",objectMapper.writeValueAsString(sendEmailDto));
//        }
//        catch(Exception e){
//            System.out.println("Something went wrong");
//        }
        return Userdto.from(user);
    }

    public SessionStatus validate(String token, Long Id) {
        Optional<Session> optionalSession = sessionRepository.findByTokenAndUser_Id(token, Id);
        if (optionalSession.isEmpty() ) { //|| optionalSession.get().getSessionStatus().equals(SessionStatus.ENDED)
            throw new InvalidTokenException("Invalid token");
        }
        Session session = optionalSession.get();
        return session.getSessionStatus();
    }
}
