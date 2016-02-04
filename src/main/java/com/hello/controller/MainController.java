package com.hello.controller;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hello.model.ColaborativeSimilarity;
import com.hello.model.Track;
import com.hello.model.TrackDTO;
import com.hello.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private TrackService trackService;

    @Autowired
    private TagSimilarityService tagSimilarityService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private CollaborativeSimilarityService collaborativeSimilarityService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/findSimilarities", method = RequestMethod.POST)
    public ModelAndView getSimilarities(@RequestParam(value = "trackId", required = false) Integer[] trackIds) {
        if (trackIds == null) {
            return new ModelAndView("redirect:/");
        }
        Set<Integer> trackIdsSet = new HashSet<>();
        Collections.addAll(trackIdsSet, trackIds);
        List<ColaborativeSimilarity> collaborativeSimilarities = collaborativeSimilarityService.getSimilarTracks(trackIdsSet);

        List<TrackDTO> orderedSimilarTracks = normalizeSimilarities(collaborativeSimilarities);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("similarTracks");
        modelAndView.addObject("queryTracks", trackService.findAllById(trackIdsSet));
        modelAndView.addObject("trackDTOs", orderedSimilarTracks);
        return modelAndView;
    }

    @RequestMapping(value = "/tracksList", method = RequestMethod.GET)
    public ModelAndView validTracks(@RequestParam String input) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("tracks", getValidTracks(input));
        return modelAndView;
    }

    @RequestMapping(value = "/tracksListJson", method = RequestMethod.GET)
    @ResponseBody
    public List<Track> validTracksJson(@RequestParam String input) {
        return getValidTracks(input);
    }

    private List<Track> getValidTracks(String input) {
        Set<Integer> artists = artistService.getArtistsByName(input);
        List<Track> tracks = trackService.getTracksByNameOrArtist(input, artists);
        List<Track> result = new ArrayList<>();
        Map<Integer, Integer> artistCount = new HashMap<>();
        for (Track track : tracks) {
            Integer id = track.artist.id;
            if (artistCount.containsKey(id)) {
                if (artistCount.get(id) == 10) {
                    continue;
                }
                artistCount.put(id, artistCount.get(id) + 1);
                result.add(track);
            } else {
                artistCount.put(id, 1);
                result.add(track);
            }
        }
        return result;
    }

    @RequestMapping(value = "/tagSimilarTracks", method = RequestMethod.GET)
    public ModelAndView tagSimilarTracks(@RequestParam Integer trackID) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("similarTracks");
        modelAndView.addObject("queryTrack", trackService.findById(trackID));
        modelAndView.addObject("tracks", tagSimilarityService.getSimilarTracks(trackID));
        return modelAndView;
    }

    // ############################################ Checking methods ##################################################
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public ModelAndView greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("greetings");
        modelAndView.addObject("name", name);
        return modelAndView;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView getUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userDetails");
        modelAndView.addObject("user", userService.findUser(1));
        return modelAndView;
    }

    private List<TrackDTO> normalizeSimilarities(List<ColaborativeSimilarity> colaborativeSimilarities) {

        Map<Integer,Map<Integer, Double>> similaritiesForSongs = Maps.newHashMap();

        ListMultimap<Integer,Integer> songToGroups = ArrayListMultimap.create();

        Map<Integer, Track> idToTrack = Maps.newHashMap();

        for(ColaborativeSimilarity colaborativeSimilarity : colaborativeSimilarities) {
            int songToFindSimilarities = colaborativeSimilarity.getTrack1().getId();

            if (!similaritiesForSongs.containsKey(songToFindSimilarities)) {
                Map<Integer,Double> similaritiesForSong = Maps.newHashMap();
                similaritiesForSongs.put(songToFindSimilarities,similaritiesForSong);
            }
            Map<Integer,Double> similaritiesForSong = similaritiesForSongs.get(songToFindSimilarities);

            Integer trackId2 = colaborativeSimilarity.getTrack2().getId();
            similaritiesForSong.put(trackId2, colaborativeSimilarity.getSimilarity());
            songToGroups.put(trackId2,songToFindSimilarities);

            idToTrack.put(trackId2, colaborativeSimilarity.getTrack2());
        }

        Map<Integer, Map<Integer, Double>> similaritiesForSongNormalized = maxMinNormalize(similaritiesForSongs);

        List<TrackDTO> result = Lists.newArrayList();

        for(Integer similarTrackId : songToGroups.keySet()) {
            List<Integer> preferencedTrackIds = songToGroups.get(similarTrackId);

            double totalSimilarity = 0.0;
            for(Integer preferenceTrack : preferencedTrackIds) {
                totalSimilarity += similaritiesForSongNormalized.get(preferenceTrack).get(similarTrackId);
            }

            totalSimilarity/=preferencedTrackIds.size();

            TrackDTO trackDTO = new TrackDTO(idToTrack.get(similarTrackId),totalSimilarity);
            result.add(trackDTO);
        }

        Collections.sort(result);
        return result;
    }

    private Map<Integer, Map<Integer, Double>> maxMinNormalize(Map<Integer, Map<Integer, Double>> similaritiesForSongs) {
        Map<Integer, Map<Integer, Double>> songSimilarityValuesNormalized = Maps.newHashMap();

        for(Map.Entry<Integer,Map<Integer,Double>> songSimilarityValues : similaritiesForSongs.entrySet()) {
            int trackId = songSimilarityValues.getKey();
            Map<Integer,Double> similarSongsValues = songSimilarityValues.getValue();

            double max = findMax(similarSongsValues.values());
            double min = findMin(similarSongsValues.values());

            Map<Integer,Double> similarSongsValuesNormalized = Maps.newHashMap();

            for(Integer key : similarSongsValues.keySet()) {
                double similarityValue = similarSongsValues.get(key);
                similarityValue = (similarityValue - min)/(max - min);
                similarSongsValuesNormalized.put(key, similarityValue);
            }

            songSimilarityValuesNormalized.put(trackId,similarSongsValuesNormalized);
        }
        return songSimilarityValuesNormalized;
    }

    private double findMax(Collection<Double> values) {
        double max = Double.MIN_VALUE;

        for(Double value : values) {
            if(Double.compare(value, max) > 0){
                max = value;
            }
        }
        return max;
    }

    private double findMin(Collection<Double> values) {
        double min = Double.MAX_VALUE;

        for(Double value : values) {
            if (Double.compare(value, min) < 0){
                min = value;
            }
        }
        return min;
    }
}
