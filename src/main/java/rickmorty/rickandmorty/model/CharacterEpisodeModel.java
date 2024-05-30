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


@Entity
@Table(name = "character_episodes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterEpisodeModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id")
    @Id
    private CharacterModel characterModel;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "episode_id")
    @Id
    private EpisodeModel episodeModel;
}

