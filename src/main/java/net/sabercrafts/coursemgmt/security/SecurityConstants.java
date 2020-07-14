package net.sabercrafts.coursemgmt.security;

public class SecurityConstants {

	public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer "; 
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/v1/users";
    public static final String LOGIN_URL = "/api/v1/users/login";
    public static final String TOKEN_SECRET="kGwqfT3ys63PpX4866YjXeb";
    
}
