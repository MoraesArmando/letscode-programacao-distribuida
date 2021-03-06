package br.com.letscode.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity(name = "authorities")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements GrantedAuthority {

    @EmbeddedId
    private AuthorityKey authorityKey;

    @ManyToOne
    @MapsId("userName")
    @JoinColumn(name = "username")
    private User user;

    public static Authority convert(User user, String role) {
        Authority authority = new Authority();

        AuthorityKey key = new AuthorityKey(user.getUserName(), role);
        authority.setUser(user);
        authority.setAuthorityKey(key);

        return authority;
    }

    @Override
    public String getAuthority() {
        return authorityKey.getAuthority();
    }
}
