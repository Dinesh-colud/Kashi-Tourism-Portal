package com.project.loginPage.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<com.project.loginPage.entities.UserFeedback,Long> {

}
