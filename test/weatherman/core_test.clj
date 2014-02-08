(ns weatherman.core-test
  (:require [clojure.test :refer :all]
            [weatherman.core :refer :all]))
(deftest f-to-c-test
  (is (= (farenheit-to-celcius 54) "12.222222")))

(deftest get-item-test
  (is (= (get-item {"query" {"results" {"channel" {"item" "hello"}}}}) "hello")))

(deftest get-temp-test
  (is (= (get-temp {"condition" {"temp" 54}}) 54)))

(deftest get-forecast-test
  (is (= (get-forecast {"forecast" "rainy"}) "rainy")))

(deftest get-condition-text-test
  (is (= (get-condition-text {"condition" {"text" "sunny"}}) "sunny")))
