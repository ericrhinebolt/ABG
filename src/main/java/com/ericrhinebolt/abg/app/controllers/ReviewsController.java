package com.ericrhinebolt.abg.app.controllers;

import com.ericrhinebolt.abg.app.data.GamesRepository;
import com.ericrhinebolt.abg.app.data.ReviewsRepository;
import com.ericrhinebolt.abg.app.data.UserRepository;
import com.ericrhinebolt.abg.app.models.Games;
import com.ericrhinebolt.abg.app.models.Reviews;
import com.ericrhinebolt.abg.app.models.User;
import com.ericrhinebolt.abg.app.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ReviewsController {
    @Autowired
    private ReviewsRepository reviewsRepository;
    @Autowired
    private GamesRepository gamesRepository;
    @Autowired
    private UserRepository userRepository;

    private final ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @GetMapping("/reviews/new_review/{appid}")
    public String gameInfo(@PathVariable String appid, Model model) {
        model.addAttribute("reviews", new Reviews());
        model.addAttribute("appId", appid);
        return "new_review";
    }

    @PostMapping("/submit_review")
    public String submitReview(Reviews reviews,
                             @RequestParam(value = "appId") int appId,
                             @RequestParam(value = "userId") String userId){
        Games g = gamesRepository.findGameByAppId(appId);
        User u = userRepository.findByUserName(userId);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDateTime localDateTime = LocalDateTime.now();
        reviews.setDate(dateTimeFormatter.format(localDateTime));
        reviews.setReviewGame(g);
        reviews.setReviewUser(u);
        reviewsRepository.save(reviews);
        return "redirect:/reviews";
    }

    @GetMapping("/reviews")
    public String reviews(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                        @RequestParam(value = "size", required = false, defaultValue = "20") int size, Model model) {
        model.addAttribute("reviews", reviewsService.getPage(pageNumber, size));
        return "reviews";
    }

    //changed above and below to 20

    @GetMapping("/reviews/search_results/{title}")
    public String searchResults(@PathVariable String title,
                                @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                @RequestParam(value = "size", required = false, defaultValue = "20") int size, Model model) {
        model.addAttribute("review", reviewsService.getPage(pageNumber, size, title));
        return "search_reviews";
    }

    @DeleteMapping("/reviews/delete/{reviewId}")
    public String deleteReview(@PathVariable int reviewId, RedirectAttributes redirectAttributes){
        reviewsRepository.deleteById(reviewId);
        redirectAttributes.addFlashAttribute("success", "Review successfully deleted.");
        return "redirect:/reviews";
    }

    @GetMapping("/reviews/update/{reviewId}")
    public String updateReview(@PathVariable int reviewId, Model model){
        model.addAttribute("reviewId", reviewId);
        Reviews reviews = reviewsRepository.findReviewsById(reviewId);
        model.addAttribute("reviews", reviews);
        Games game = reviews.getReviewGame();
        model.addAttribute("game", game);
        return "update_review";
    }

    @RequestMapping(value = "/update",  method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(Reviews reviews, @RequestParam(value = "updateReviewId") int updateReviewId){
        String review = reviews.getReview();
        Reviews oldReview = reviewsRepository.findReviewsById(updateReviewId);
        oldReview.setReview(review);
        reviewsRepository.save(oldReview);
        return "redirect:/reviews";
    }
}

