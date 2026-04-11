package com.project.loginPage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private com.project.loginPage.repositorys.FeedbackRepository feedbackRepository;

    @Override
    public com.project.loginPage.entities.UserFeedback save(com.project.loginPage.entities.UserFeedback userFeedback) {

        return feedbackRepository.save(userFeedback);
    }
}
