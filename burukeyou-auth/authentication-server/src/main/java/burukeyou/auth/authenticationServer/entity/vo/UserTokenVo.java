package burukeyou.auth.authenticationServer.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@ToString
public class UserTokenVo  implements UserDetails {

	private String id;

	private String username;

	private String password;

	private String nickname;

	private String avatar;

	private Boolean enabled;

	private Boolean accountNonExpired;

	private Boolean credentialsNonExpired;

	private Boolean accountNonLocked;

	private Collection<? extends GrantedAuthority> authorities;

	@JsonIgnore
	private List<String> roles;     // 用户拥有的角色code列表

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
