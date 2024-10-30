package ru.sakhdanil.TechRadar.config;

import org.springframework.http.HttpMethod;

record privacyEndPoint(String endPoint, HttpMethod method) {
}