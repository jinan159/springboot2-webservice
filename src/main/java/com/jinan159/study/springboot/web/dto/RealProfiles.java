package com.jinan159.study.springboot.web.dto;

public enum RealProfiles {
    DEFAULT,
    REAL,
    REAL1;

    /*
        열거형은 대문자가 컨벤션인데, profile은 소문자가 컨벤션이라서
        toString()을 오버라이드하여 소문자로 출력되도록 함.
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}