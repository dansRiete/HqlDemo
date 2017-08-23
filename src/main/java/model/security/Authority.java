package model.security;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authority {
    public enum AuthorityType {
        TEACHER, ADMIN, PARENT, CHILD
    }

    @Id
    @Column(name = "name", nullable = false, length = 36)
    @Enumerated(EnumType.STRING)
    private AuthorityType authority;

    public Authority() {
    }
    
    public Authority(AuthorityType authority) {
        this.authority = authority;
    }
   
    public void setAuthority(AuthorityType authority) {
        this.authority = authority;
    }
}
