/**
 * Created by Denys Durbalov
 * Date of creation: 5/30/24
 * Project name: RickAndMorty
 * email: den.dyrbalov25@gmail.com or s28680@pjwstk.edu.pl
 */

package rickmorty.rickandmorty.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "episodes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpisodeModel {
    @Id
    private Integer id;
    private String name;
    @Column(name = "air_date")
    private Date airDate;
    @Column(name = "episode_code")
    private String episodeCode;
    private String url;
    private Timestamp created;
}

