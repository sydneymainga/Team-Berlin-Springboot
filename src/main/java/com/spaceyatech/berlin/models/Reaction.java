package com.spaceyatech.berlin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spaceyatech.berlin.enums.Reaction_type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Reaction")
public class Reaction extends BaseEntity{

    @Column(name ="reaction_type",length=45)
    @Enumerated(EnumType.ORDINAL)
    private Reaction_type reaction_type;

    @Column(name ="reactionscol",length=45)
    private String reactionscol;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "blogPost_id", nullable = false)//We also use the @JoinColumn annotation to specify the foreign key column
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private BlogPost blogPost;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)//We also use the @JoinColumn annotation to specify the foreign key column
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Account account;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comment_id", nullable = false)//We also use the @JoinColumn annotation to specify the foreign key column
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Comment comment;






}
