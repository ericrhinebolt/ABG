package com.ericrhinebolt.abg.app.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Favorites {

@Id
 private int user_id;

 private int app_id;

}
