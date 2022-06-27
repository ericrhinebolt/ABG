package com.ericrhinebolt.abg.ABG.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Favorites {

//  Entity Model for favorites table
@Id
 private int user_id;

 private int app_id;

}
