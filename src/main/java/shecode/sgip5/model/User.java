package shecode.sgip5.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private Integer role = 1;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "status")
    private Boolean status = true;

    @OneToOne
    @JoinColumn(name = "university_id")
    private University university;

}
