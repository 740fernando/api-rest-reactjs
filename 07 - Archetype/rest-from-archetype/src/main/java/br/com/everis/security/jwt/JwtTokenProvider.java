package br.com.everis.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.everis.exception.InvalidJwtAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Classe representa o valor padrao que será definido na securityKey e
 * validityInMilliSeconds caso nao, seja definido valor no
 * application.properties
 * 
 * @author fsouviei
 *
 */
@Service
public class JwtTokenProvider {

	private static final String EXPIRED_OR_INVALID_TOKEN = "Expired or invalid token";

	private static final String AUTHORIZATION = "Authorization";

	private static final String BEARER = "Bearer ";

	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey = "secret";

	@Value("${security.jwt.token.expire-length:3600000}")
	private long validityInMilliseconds = 3600000; // == one hour

	@Autowired
	private UserDetailsService userDetailsService;

	@PostConstruct // responsavel por encodar a secret key
	public void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	// Responsavel por criar o token
	public String createToken(String username, List<String> roles) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("roles", roles);

		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);
		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUserName(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	private String getUserName(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader(AUTHORIZATION);
		return (bearerToken != null && bearerToken.startsWith(BEARER)) ? bearerToken.substring(7, bearerToken.length())
				: null;
	}

	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return (claims.getBody().getExpiration().before(new Date())) ? false : true;
		} catch (Exception e) {
			throw new InvalidJwtAuthenticationException(EXPIRED_OR_INVALID_TOKEN);
		}
	}
}
