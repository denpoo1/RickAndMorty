/**
 * Created by Denys Durbalov
 * Date of creation: 5/30/24
 * Project name: RickAndMorty
 * email: den.dyrbalov25@gmail.com or s28680@pjwstk.edu.pl
 */

package rickmorty.rickandmorty.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Entity
@Table(name = "characters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterModel {
    @Id
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_id")
    private LocationModel origin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private LocationModel locationModel;
    @Column(name = "image_url")
    private String imageUrl;
    private String url;
    private Timestamp created;
}
