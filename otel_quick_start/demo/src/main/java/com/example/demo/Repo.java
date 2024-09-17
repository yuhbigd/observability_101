package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo extends JpaRepository<Test, Long> {
}
