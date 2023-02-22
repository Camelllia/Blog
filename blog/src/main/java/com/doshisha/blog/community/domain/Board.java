package com.doshisha.blog.community.domain;

import com.doshisha.blog.member.domain.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "board_title", nullable = false)
    private String title;

    @Column(name = "board_content", nullable = false)
    private String content;

    @Column(name = "delYn", nullable = false)
    @ColumnDefault("false")
    private Boolean delYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private BoardMenu boardMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    @Builder
    public Board(String title, String content, BoardMenu boardMenu, Member member) {
        this.title = title;
        this.content = content;
        this.boardMenu = boardMenu;
        this.member = member;
    }
}
