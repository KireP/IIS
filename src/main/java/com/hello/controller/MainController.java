package com.hello.controller;

import com.hello.model.Track;
import com.hello.service.TagSimilarityService;
import com.hello.service.TrackService;
import com.hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private TrackService trackService;

    @Autowired
    private TagSimilarityService tagSimilarityService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/tracksList", method = RequestMethod.GET)
    public ModelAndView validTracks(@RequestParam String trackName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("tracks", trackService.getTracksByName(trackName));
        return modelAndView;
    }

    @RequestMapping(value = "/tracksListJson", method = RequestMethod.GET)
    @ResponseBody
    public List<Track> validTracksJson(@RequestParam String trackName) {
        return trackService.getTracksByName(trackName);
    }

    @RequestMapping(value = "/tagSimilarTracks", method = RequestMethod.GET)
    public ModelAndView tagSimilarTracks(@RequestParam Integer trackID) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("similarTracks");
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
}
