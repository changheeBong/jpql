package jpql;

import javax.persistence.*;

@Entity
@NamedQuery(
        name = "Member.findByUsername",
        query = "select m from Member m where m.userName = :username"
)
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String userName;
    private int age;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }
}
