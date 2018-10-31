package graphql.model;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GraphQLQuery(name = "id")
    private String id;

    @Column(name = "user_username", nullable = false)
    @GraphQLQuery(name = "username")
    private String username;

    @Column(name = "user_email", nullable = false)
    @GraphQLQuery(name = "email")
    private String email;

    @Column(name = "user_request_count", nullable = false)
    @GraphQLQuery(name = "requestCount")
    private int requestCount;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false, updatable = false)
    @GraphQLQuery(name = "role")
    private Role role;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "user_state", nullable = false)
    @GraphQLQuery(name = "state")
    private State state;

    public enum State {
        ACTIVE,
        DISABLED,
        BLOCKED
    }
}
