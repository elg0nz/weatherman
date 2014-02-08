(ns weatherman.formaters-test
  (:require [clojure.test :refer :all]
            [weatherman.formaters :refer :all]))

(deftest get-item-test
  (is (= (get-item {"query" {"results" {"channel" {"item" "hello"}}}}) "hello")))

(deftest get-temp-test
  (is (= (get-temp {"condition" {"temp" 54}}) 54)))

(deftest get-forecast-test
  (is (= (get-forecast {"forecast" "rainy"}) "rainy")))

(deftest get-condition-text-test
  (is (= (get-condition-text {"condition" {"text" "sunny"}}) "sunny")))
