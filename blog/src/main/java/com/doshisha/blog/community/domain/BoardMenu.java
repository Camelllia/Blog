package com.doshisha.blog.community.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@Table(name = "board_menu")
public class BoardMenu {

    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menu_name", nullable = false, unique = true)
    private String menuName;

    @Column(name = "menu_path", nullable = false, unique = true)
    private String menuPath;

    @Column(name = "writeYn", nullable = false)
    @ColumnDefault("true")
    private Boolean writeYn;

    @Column(name = "delYn", nullable = false)
    @ColumnDefault("false")
    private Boolean delYn;

    @Column(name = "reg_date")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    @Builder
    public BoardMenu(Long id, String menuName, String menuPath, Boolean writeYn, Boolean delYn) {
        this.id = id;
        this.menuName = menuName;
        this.menuPath = menuPath;
        this.writeYn = writeYn;
        this.delYn = delYn;
    }
}
