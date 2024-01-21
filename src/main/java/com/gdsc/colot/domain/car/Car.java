package com.gdsc.colot.domain.car;

import com.gdsc.colot.domain.BaseEntity;
import com.gdsc.colot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "CAR")
@NoArgsConstructor
public class Car extends BaseEntity {

    @Column(nullable = false)
    private String plateNum;

    private String nickname;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "repCar")
    private User user4Rep;

    @Builder
    public Car(String plateNum, String nickname, User user) {
        this.plateNum = plateNum;
        this.nickname = nickname;
        this.user = user;
    }

    public void linkUser4Rep(User user) {
        this.user4Rep = user;
    }

}
