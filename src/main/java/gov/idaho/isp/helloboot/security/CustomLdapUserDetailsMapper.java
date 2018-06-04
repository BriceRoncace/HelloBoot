package gov.idaho.isp.helloboot.security;

import gov.idaho.isp.helloboot.user.User;
import java.util.Collection;
import java.util.HashSet;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

public class CustomLdapUserDetailsMapper extends LdapUserDetailsMapper {
  public static final String LDAP_ATTR_USERNAME = "sAMAccountName";
  public static final String LDAP_ATTR_FIRST_NAME = "givenName";
  public static final String LDAP_ATTR_LAST_NAME = "sn";
  public static final String LDAP_ATTR_EMAIL = "mail";

  @Override
  public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
    DirContextAdapter adapter = new DirContextAdapter(ctx.getAttributes(), ctx.getDn());
    return buildUser(adapter, authorities);
  }

  private User buildUser(DirContextAdapter adapter, Collection<? extends GrantedAuthority> authorities) {
    User user = new User();
    user.setUsername(adapter.getStringAttribute(LDAP_ATTR_USERNAME));
    user.setFirstName(adapter.getStringAttribute(LDAP_ATTR_FIRST_NAME));
    user.setLastName(adapter.getStringAttribute(LDAP_ATTR_LAST_NAME));
    user.setEmail(adapter.getStringAttribute(LDAP_ATTR_EMAIL));
    user.setAuthorities(new HashSet<>(authorities));
    return user;
  }
}
