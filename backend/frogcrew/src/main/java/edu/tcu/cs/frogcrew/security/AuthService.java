package edu.tcu.cs.frogcrew.security;

import edu.tcu.cs.frogcrew.crewmember.CrewMember;
import edu.tcu.cs.frogcrew.crewmember.MyUserPrincipal;
import edu.tcu.cs.frogcrew.crewmember.converter.CrewMemberToLoginDtoConverter;
import edu.tcu.cs.frogcrew.crewmember.dto.LoginDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final JwtProvider jwtProvider;
    private final JwtDecoder jwtDecoder;
    private final CrewMemberToLoginDtoConverter crewMemberToLoginDtoConverter;

    public AuthService(JwtProvider jwtProvider, JwtDecoder jwtDecoder, CrewMemberToLoginDtoConverter crewMemberToLoginDtoConverter) {
        this.jwtProvider = jwtProvider;
        this.jwtDecoder = jwtDecoder;
        this.crewMemberToLoginDtoConverter = crewMemberToLoginDtoConverter;
    }

    public Map<String, Object> createLoginInfo(Authentication authentication) {
        // user info
        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
        CrewMember member = principal.getCrewMember();
        LoginDto loginDto = crewMemberToLoginDtoConverter.convert(member);

        // jwt
        String token = this.jwtProvider.createToken(authentication);
        Map<String, Object> loginInfo = new HashMap<>();
        loginInfo.put("userId", loginDto.userId());
        loginInfo.put("role", loginDto.role());
        loginInfo.put("token", token);

        return loginInfo;
    }

    public boolean validateInviteToken(String token) {
        try {
            Jwt decodedJwt = jwtDecoder.decode(token);
            return true; // If decoding succeeds
        } catch (JwtException e) {
            return false; // If token is invalid or expired
        }
    }
}
